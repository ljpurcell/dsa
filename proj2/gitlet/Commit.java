package gitlet;

import java.io.Serial;
import java.util.Date;
import java.util.Map;

import static gitlet.Repository.*;
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

    @Serial
    private static final long serialVersionUID = 1234567L;

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

       boolean isFirstCommit = setUpCommitBasedOnParent();

        author = auth;
        message = msg;
        key = createCommitKey(dateTime, message, treeRef);

        if (!isFirstCommit) {
            updateBasedOnStagedFiles();
        }

        writeToDisk();
        moveHeadPointerTo(key);
        Repository.clearStagingMapAndIndexFile();
    }

    public static Commit getCommit(String idKey) {
        return readObjectFromDisk(idKey, Commit.class);
    }

    private boolean setUpCommitBasedOnParent() {
        boolean firstCommit = false;
        String headKey = readContentsAsString(join(GITLET_DIR, "HEAD"));

        if (headKey.equals("")) {
            dateTime = new Date(0);
            treeRef = new Tree().key();
            firstCommit = true;
        } else {
            Commit parent = Commit.getCommit(headKey);
            treeRef = parent.treeRef();
            dateTime = new Date();
        }

        return firstCommit;
    }

    public void updateBasedOnStagedFiles() {
        Tree t = Tree.getTree(treeRef);
        STAGING_MAP = Repository.getStagingMap();

        if (STAGING_MAP.isEmpty()) {
            System.out.println("No changes added to the commit.");
        }
        else {
            Map<String, String> treeMap = t.getFileBlobMap();
            for (String file: STAGING_MAP.keySet()) {
                if (STAGING_MAP.get(file) != null) {
                    Blob b = t.getBlobUsingFileName(file);
                    if (b != null) {
                        treeMap.replace(file, STAGING_MAP.get(file));
                    }
                }
                else {
                    treeMap.remove(file);
                }
            }
        }
    }

    private static String createCommitKey(Date date, String msg, String tree) {
        String commitString = date + msg + tree;
        return Utils.sha1(commitString);
    }

    public static Commit getHeadCommit() {
        String headKey = readContentsAsString(HEAD_FILE).trim();
        return readFromDisk(headKey);
    }

    public static Tree getLastCommitTree() {
        Commit headCommit = Commit.getHeadCommit();
        String headTreeRef = headCommit.treeRef();
        return Tree.getTree(headTreeRef);
    }

    public static Commit readFromDisk(String idKey) {
        return readObjectFromDisk(idKey, Commit.class);
    }

    public String key() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    public boolean isTrackingFile(String filename) {
        Tree t = Tree.getTree(treeRef);
        return t.getFileBlobMap().containsKey(filename);
    }

    public String treeRef() {
        return treeRef;
    }

    private void moveHeadPointerTo(String k) {
        writeContents(join(HEAD_FILE), k);
    }
}
