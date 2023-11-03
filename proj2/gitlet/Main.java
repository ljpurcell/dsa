package gitlet;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author ljpurcell
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // TODO: if args is empty, show help
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                Repository.initialiseGitletRepo();
                break;
            case "add":
                // TODO: handle the `add [filename]` command
                break;
            // TODO: FILL THE REST IN
        }
    }
}
