package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private T maxValue;
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
       comparator = c;
       maxValue = this.max();
    }

    public T max() {
        return maxValue != null ? maxValue : max(comparator);
    }

    public T max(Comparator<T> c) {
        maxValue = null;
        for (int i = 0; i < super.size(); i++) {
            if (maxValue == null || (c.compare(super.get(i), maxValue) > 0)) {
                maxValue = super.get(i);
            }
        }

        return maxValue;
    }
}
