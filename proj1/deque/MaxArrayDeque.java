package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> _comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        _comparator = c;
    }

    /**
     * Returns the maximum element according to the instance Comparator.
     * @return The maximum element, or null if empty
     */
    public T max() {
        return max(_comparator);
    }

    /**
     * Returns the maximum element according to the parameter Comparator.
     * Does not change the instance comparator.
     * @param c Comparator to use for finding maximum
     * @return The maximum element according to c, or null if empty
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
