package gitlet;

/**
 * Represents a gitlet blob object.
 * Blobs are gitlet's method of representing a version of a file.
 *
 * @author ljpurcell
 */
public class Blob {
    String key;
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
    }
}