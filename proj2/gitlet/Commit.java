package gitlet;

import java.util.Date;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author ljpurcell
 */
public class Commit {
    /**
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used.
     */

    private String id; // Hex-string from commit hash?

    /** The message of this Commit. */
    private String message;

    /** The creator of the Commit. */
    private String author;

    /** The date the Commit was created. */
    private Date date = new Date();

    /** The time the Commit was created. */
    private long unixTime = new Date().getTime();

    /** A hex-string reference to the repo state */
    private String parentCommitRef;

    /** A hex-string reference to the repo state */
    private String repoStateRef;

    public Commit(String msg, String auth) {
        message = msg;
        author = auth;
    }
}
