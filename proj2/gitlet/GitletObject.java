package gitlet;

import java.io.File;
import java.io.Serializable;

abstract class GitletObject implements Serializable {
    String key;

    public String key() {
        return key;
    }

    public void writeToDisk() {
        File f = getFileFromKey(key, this.getClass());
        Utils.writeObject(f, this);
    }

    protected static <T extends GitletObject> T readObjectFromDisk(String key, Class<T> objectClass) {
        if (objectClass.equals(Commit.class) || objectClass.equals(Tree.class) || objectClass.equals(Blob.class)) {
            File file = getFileFromKey(key, objectClass);
            if (file == null) {
                return null;
            }

            return Utils.readObject(file, objectClass);
        }

        throw new GitletException("Class not recognised Gitlet object: " + objectClass);
    }

    private static File getFileFromKey(String key, Class<?> objectClass) {
        if (key == null) {
            return null;
        }

        String classDir;
        if (objectClass.equals(Commit.class)) {
            classDir = "commits";
        } else if (objectClass.equals(Tree.class)) {
            classDir = "trees";
        } else if (objectClass.equals(Blob.class)) {
            classDir = "blobs";
        } else {
            throw new GitletException("Class not recognised Gitlet object: " + objectClass);
        }

        return Utils.join(Repository.GITLET_DIR, "objects", classDir, key);
    }
}
