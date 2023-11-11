package gitlet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a gitlet tree object.
 * Trees are gitlet's method of representing a version of a directory.
 * Tree objects can have nodes of blobs (files) or other tree objects (subdirectories).
 *
 * @author ljpurcell
 */

public class Tree extends GitletObject implements Serializable {
    /**
     * File Name -> Blob Key
     */
    HashMap<String, String> fileBlobMap;

   public Tree() {
       fileBlobMap = new HashMap<>();
       key = Utils.sha1("tree "  + fileBlobMap);
       writeToDisk();
   }

   public static Tree getTree(String k) {
       return readObjectFromDisk(k, Tree.class);
   }

   public Blob getBlobUsingFileName(String fileName) {
       Blob fileBlob = null;
       if (fileBlobMap.containsKey(fileName)) {
           String blobKey = fileBlobMap.get(fileName);
           fileBlob = Blob.readFromDisk(blobKey);
       }
       return fileBlob;
   }

   public Map<String, String> getFileBlobMap() {
       return fileBlobMap;
   }
}
