package gitlet;

import java.util.HashMap;
import java.util.Map;

/**
 * Driver class for Gitlet, a subset of the Git version-control system.
 *
 * @author ljpurcell
 */
public class Main {

    /**
     * Usage: java gitlet.Main ARGS, where ARGS contains
     * <COMMAND> <OPERAND1> <OPERAND2> ...
     */
    public static void main(String[] args) {
        // TODO: if args is empty, show help
        // TODO: Consider making enum of valid commands
        String firstArg = args[0];
        if (!Repository.GITLET_DIR.exists() && !firstArg.equals("init")) {
            System.out.println("Not in an initialized Gitlet directory");
        } else {

            switch (firstArg) {
                case "init" -> Repository.initialiseGitletRepo();
                case "add" -> {
                    if (args.length == 2) {
                        Repository.addFileToStagingArea(args[1]);
                    }
                }
                case "commit" -> {
                    if (args.length < 2) {
                        System.out.println("Please enter a commit message.");
                    }
                    else {
                        Repository.createCommit(args[1]);
                    }
                }
                case "rm" -> Repository.removeFile(args[1]);
                case "log" -> Repository.commitLog();
                case "find" -> Repository.findCommitsWithMessage(args[1]);
                case "test" -> {
                    Commit c = Commit.getHeadCommit();
                    Tree t = Tree.getTree(c.treeRef());
                    Map<String, String> m = t.getFileBlobMap();
                    System.out.println(m);
                }
            }
        }
    }
}
