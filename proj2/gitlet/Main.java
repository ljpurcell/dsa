package gitlet;

import java.util.Arrays;

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
                    String[] files = Arrays.copyOfRange(args, 1, args.length);
                    Repository.addFilesToStagingArea(files);
                }
                // TODO: FILL THE REST IN
            }
        }
    }
}
