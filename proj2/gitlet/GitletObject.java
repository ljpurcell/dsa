package gitlet;

import java.io.Serializable;

abstract class GitletObject implements Serializable {
    String key;

    public String key() {
        return key;
    }
}
