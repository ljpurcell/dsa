package gitlet;

import java.io.*;
import java.util.HashMap;

import static gitlet.Utils.*;


/**
 * Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 * @author ljpurcell
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /**
     * The current working directory.
     */
    public static final File CWD = new File(System.getProperty("user.dir"));

    /**
     * The .gitlet directory.
     */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    /**
     * HEAD file.
     */
    public static final File HEAD_FILE = join(GITLET_DIR, "HEAD");

    /**
     * INDEX file.
     */
    public static final File INDEX_FILE = join(GITLET_DIR, "index");

    /**
     * OBJECTS directory
     */
    public static final File OBJECTS_DIR = join(GITLET_DIR, "objects");

    /**
     * Staging area, maps file names to blob keys
     */
    public static HashMap<String, String> STAGING_MAP = new HashMap<>();

    public static void initialiseGitletRepo() {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
        } else if (!GITLET_DIR.mkdir()) {
            throw new GitletException("Could not initialise Gitlet repository");
        } else {
            createGitletSubStructure();
            createInitialCommit();
        }
    }

    /**
     * Sets up the Gitlet directory sub folders and files. Mirrors Git.
     * HEAD: File. Pointer to current object.
     * OBJECTS: Directory. Objects (blobs, trees, and commits) stored in sub-folders of first two chars of hash id.
     * REFS: Directory. Stores named references of hashed objects.
     */
    static private void createGitletSubStructure() {
        boolean head, index, blobs, trees, commits;

        try {
            head = HEAD_FILE.createNewFile();
            index = INDEX_FILE.createNewFile();
        } catch (Exception e) {
            throw new GitletException("Could not create HEAD or index file: " + e);
        }

        blobs = join(OBJECTS_DIR, "blobs").mkdirs();
        trees = join(OBJECTS_DIR, "trees").mkdirs();
        commits = join(OBJECTS_DIR, "commits").mkdirs();

        if (!(blobs && trees && commits)) {
            throw new GitletException("Created: blobs/" + blobs + ". trees/" + trees + ". commits/" + commits);
        }
    }

    static private void createInitialCommit() {
        new Commit("Initial commit", "ljpurcell");
    }

    static void addFileToStagingArea(String... args) {

        setStagingMapFromIndexFile();

        Tree headTree = Commit.getLastCommitTree();

        for (String file : args) {
            String text = readContentsAsString(join(CWD, file));
            Blob newBlob = new Blob(text);
            if (STAGING_MAP.containsKey(file)) {
                Blob lastBlob = headTree.getBlobUsingFileName(file);
                if (newBlob.key().equals(lastBlob.key())) {
                    STAGING_MAP.remove(file);
                } else {
                    STAGING_MAP.replace(file, newBlob.key());
                }
            } else {
                STAGING_MAP.put(file, newBlob.key());
            }
        }

        writeStagingAreaToIndexFile();
    }

    private static void setStagingMapFromIndexFile() {
        String stagingString = readContentsAsString(INDEX_FILE);
        if (!stagingString.equals("")) {
            stagingString = stagingString.substring(1, stagingString.length() - 1);
            String[] pairs = stagingString.split(",");

            for (String p : pairs) {
                p = p.trim();
                int equals = p.lastIndexOf("=");
                String key = p.substring(0, equals);
                String val = p.substring(equals + 1);
                STAGING_MAP.put(key, val);
            }
        }
    }

    private static void writeStagingAreaToIndexFile() {
        writeContents(INDEX_FILE, STAGING_MAP.toString());
    }
}
