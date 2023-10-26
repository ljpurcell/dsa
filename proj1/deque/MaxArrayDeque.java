package deque;

import java.util.Collections;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private T maxValue;
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
       comparator = c;
       maxValue = this.max();
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        maxValue = null;
        for (int i = 0; i < super.size(); i++) {
            if (maxValue == null || (c.compare(maxValue, super.get(i))) > 0) {
                maxValue = super.get(i);
            }
        }

        return maxValue;
    }
}
