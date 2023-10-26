package deque;

import java.util.Comparator;

public class MaxArrayDequeComparators {
    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    private static class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

    public static Comparator<Integer> getIntegerComparator() {
        return new IntegerComparator();
    }

    public static Comparator<String> getStringComparator() {
        return new StringComparator();
    }
}
