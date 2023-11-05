package gitlet;

import java.io.File;
import java.util.HashSet;

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
        boolean head, objects, refs;

        try {
            head = join(GITLET_DIR, "HEAD").createNewFile();
        } catch (Exception e) {
            throw new GitletException("Could not create HEAD file: " + e);
        }

        objects = join(GITLET_DIR, "objects").mkdir();
        refs = join(GITLET_DIR, "refs").mkdir();

        if (!(head && objects && refs)) {
            throw new GitletException("Created: HEAD - " + head + ". objects/ - " + objects + ". refs/ - " + refs);
        }
    }

    static private void createInitialCommit() {
        Commit c = new Commit("Initial commit", "ljpurcell");
    }
}
