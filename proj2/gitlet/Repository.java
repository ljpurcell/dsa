package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
     * Staging area, maps file names to blob keys
     */
    public static Map<String, String> STAGING_AREA = new HashMap<>();

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
        boolean head, index, refs, blobs, trees, commits;

        try {
            head = join(GITLET_DIR, "HEAD").createNewFile();
            index = join(GITLET_DIR, "index").createNewFile();
        } catch (Exception e) {
            throw new GitletException("Could not create HEAD or index file: " + e);
        }

        refs = join(GITLET_DIR, "refs").mkdir();
        blobs = join(GITLET_DIR, "objects", "blobs").mkdirs();
        trees = join(GITLET_DIR, "objects", "trees").mkdirs();
        commits = join(GITLET_DIR, "objects", "commits").mkdirs();

        if (!(refs && blobs && trees && commits)) {
            throw new GitletException("Created: blobs/" + blobs + ". trees/" + trees + ". commits/" + commits + ". refs/" + refs);
        }
    }

    static private void createInitialCommit() {
        new Commit("Initial commit", "ljpurcell");
    }

    static void addFilesToStagingArea(String... args) {

        Tree headTree = Commit.getLastCommitTree();

        for (String file : args) {
            String text = readContentsAsString(join(CWD, file));
            Blob newBlob = new Blob(text);
            if (STAGING_AREA.containsKey(file)) {
                Blob lastBlob = headTree.getBlobUsingFileName(file);
                if (newBlob.key().equals(lastBlob.key())) {
                    STAGING_AREA.remove(file);
                } else {
                    STAGING_AREA.replace(file, newBlob.key());
                }
            } else {
                STAGING_AREA.put(file, newBlob.key());
            }
        }

        System.out.println(STAGING_AREA);
        updateStagingIndex();
    }

    private static void updateStagingIndex() {
        File indexFile = join(GITLET_DIR, "index");
        writeObject(indexFile, (Serializable) STAGING_AREA);
    }
}
