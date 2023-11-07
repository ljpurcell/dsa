package gitlet;

import java.io.File;
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
     * Staging area, maps file names to blobs
     */
    public static Map<String, Blob> STAGING_AREA = new HashMap<>();

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
        boolean head, refs, blobs, commits;

        try {
            head = join(GITLET_DIR, "HEAD").createNewFile();
        } catch (Exception e) {
            throw new GitletException("Could not create HEAD file: " + e);
        }

        refs = join(GITLET_DIR, "refs").mkdir();
        blobs = join(GITLET_DIR, "objects", "blobs").mkdirs();
        commits = join(GITLET_DIR, "objects", "commits").mkdirs();

        if (!(refs && blobs && commits)) {
            throw new GitletException("Created: objects/blobs/ - " + blobs + ". objects/commits/" + commits + ". refs/ - " + refs);
        }
    }

    static private void createInitialCommit() {
        new Commit("Initial commit", "ljpurcell");
    }

    static void addFilesToStagingArea(String... args) {
        for (String file : args) {
            String text = readContentsAsString(join(CWD, file));
            Blob blob = new Blob(text);
            if (STAGING_AREA.containsKey(file)) {
                Blob lastVersion = Blob.readFromDisk("KEY GOES HERE");
                STAGING_AREA.get(file).equals(lastVersion) ? STAGING_AREA.remove(file) : STAGING_AREA.replace(file, blob);
            } else {
                STAGING_AREA.put(file, blob);
            }
        }
    }
}
