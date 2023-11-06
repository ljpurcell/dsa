package gitlet;

import java.io.File;
import java.io.Serializable;

abstract class GitletObject implements Serializable {
    String key;

    public String key() {
        return key;
    }

    public void writeToDisk() {
        File f = getFileFromKey(key);
        Utils.writeObject(f, this);
    }

    public static GitletObject readFromDisk(String key) {
        File file = getFileFromKey(key);
        return Utils.readObject(file, GitletObject.class);
    }

    private static File getFileFromKey(String key) {
        String dir = key.substring(0, 2);
        String fileName = key.substring(2);
        return Utils.join(Repository.GITLET_DIR, "objects", dir, fileName);
    }
}
