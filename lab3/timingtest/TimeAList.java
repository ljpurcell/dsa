package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.print("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {

        AList<Integer> ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCount = new AList<>();

        for (int ops = 1000; ops <= 64000; ops *= 2) {

            Stopwatch sw = new Stopwatch();
            AList<Integer> test = new AList<>();

            for (int i = 0; i < ops; i++) {
                test.addLast(i);
            }

            double time = sw.elapsedTime();
            ns.addLast(ops);
            times.addLast(time);
            opCount.addLast(ops);
        }

        printTimingTable(ns, times, opCount);
    }
}
