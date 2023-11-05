package gitlet;

import java.io.Serializable;
import java.io.File;
import java.time.Instant;
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
public class Commit implements Serializable {
    /**
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used.
     */

    private String id; // Hex-string from commit hash?

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
    private String repoTreeRef;

    public Commit(String msg, String auth) {
        /**
         * 1. Get parent ref from HEAD
         * 2. If HEAD does not exist or is empty, this is the first commit
         * 3. If ref exists, map to object using entry in regs
         */

        String headRef = readContentsAsString(join(GITLET_DIR, "HEAD"));

        if (headRef.equals("")) {
            dateTime = new Date(0);
        }
        else {
            // get object using ref
            dateTime = new Date();
        }

        author = auth;
        message = msg;

        // still need to reference repo & create ID hash
    }

    public void writeCommitToDisk() {
        String dir = id.substring(0, 2);
        String fileName = id.substring(2);
        File file = join(GITLET_DIR, "objects", dir, fileName);
        writeObject(file, this);
    }

    public Commit readCommitFromDisk(String identifier) {
        String dir = identifier.substring(0, 2);
        String fileName = identifier.substring(2);
        File file = join(GITLET_DIR, "objects", dir, fileName);
        return readObject(file, Commit.class);
    }
}
