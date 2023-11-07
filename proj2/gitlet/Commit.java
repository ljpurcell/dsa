package gitlet;

import java.io.File;
import java.util.Date;

import static gitlet.Repository.GITLET_DIR;
import static gitlet.Utils.*;

/**
 * Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 * @author ljpurcell
 */
public class Commit extends GitletObject {
    /**
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used.
     */


    /**
     * The message of this Commit.
     */
    private String message;

    /**
     * The creator of the Commit.
     */
    private String author;

    /**
     * The date and time the Commit was created.
     */
    private Date dateTime;

    /**
     * A hex-string reference to the parent Commit of THIS
     */
    private String parentCommitRef;

    /**
     * A hex-string reference to the repo tree state
     */
    private String treeRef;

    public Commit(String msg, String auth) {
        /**
         * 1. Get parent ref from HEAD
         * 2. If HEAD does not exist or is empty, this is the first commit
         * 3. If ref exists, map to object using entry in refs
         */

        String headKey = readContentsAsString(join(GITLET_DIR, "HEAD"));

        if (headKey.equals("")) {
            dateTime = new Date(0);
        }
        else {
            Commit parent = Commit.getCommit(headKey);
            treeRef = parent.treeRef;
            dateTime = new Date();
        }

        author = auth;
        message = msg;

        updateBasedOnStagedFiles();

       this.writeToDisk();
       moveHeadPointerTo(key);
    }

    public static Commit getCommit(String idKey) {
        return readObjectFromDisk(idKey, Commit.class);
    }

    public void updateBasedOnStagedFiles() {
        /**
         * 1. Get tree using treeRef
         * 2. Get staged files
         * 3. Modify tree based on staged files
         */
    }

    public String key() {
        return key;
    }

    private void moveHeadPointerTo(String k) {
        writeContents(join(GITLET_DIR, "HEAD"), k);
    }
}
