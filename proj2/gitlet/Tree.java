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
    Map<String, String> blobMap;

   public Tree() {
       blobMap = new HashMap<>();
       key = Utils.sha1("tree "  + blobMap);
       writeToDisk();
   }

   public static Tree getTree(String k) {
       return readObjectFromDisk(k, Tree.class);
   }

   public Blob getBlobUsingFileName(String fileName) {
       Blob fileBlob = null;
       if (blobMap.containsKey(fileName)) {
           String blobKey = blobMap.get(fileName);
           fileBlob = Blob.readFromDisk(blobKey);
       }
       return fileBlob;
   }
}
