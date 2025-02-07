package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comparator = c;
    }

    /**
     * Returns the maximum element according to the instance Comparator.
     */
    public T max() {
        return max(this.comparator);
    }

    /**
     * Returns the maximum element according to the parameter Comparator.
     * Does not change the instance comparator.
     */
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T currentMax = get(0);
        for (int i = 1; i < size(); i++) {
            if (c.compare(get(i), currentMax) > 0) {
                currentMax = get(i);
            }
        }
        return currentMax;
    }
}