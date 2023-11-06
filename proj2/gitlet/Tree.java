package gitlet;

import java.util.List;

/**
 * Represents a gitlet tree object.
 * Trees are gitlet's method of representing a version of a directory.
 * Tree objects can have nodes of blobs (files) or other tree objects (subdirectories).
 *
 * @author ljpurcell
 */

public class Tree {
    private class Node<T> {
        List<Node<T>> children;
        T item;

        protected Node(T i) {
           item = i;
        }
    }
}
