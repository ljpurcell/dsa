package gitlet;

import java.util.Arrays;

/**
 * Represents a gitlet blob object.
 * Blobs are gitlet's method of representing a version of a file.
 *
 * @author ljpurcell
 */
public class Blob extends GitletObject {
    byte[] content;

    public Blob(String text) {
        String blobString = "blob " + text.length() + '\0' + text;
        key = Utils.sha1(blobString);
        try {
            content = ZLibCompression.compress(blobString);
        }
        catch (Exception e) {
            throw new GitletException("Could not compress '" + text + "': " + e);
        }

        this.writeToDisk();
    }

    public static Blob readFromDisk(String idKey) {
        return readObjectFromDisk(idKey, Blob.class);
    }

    public String getContentString() {
        return Arrays.toString(content);
    }
}
