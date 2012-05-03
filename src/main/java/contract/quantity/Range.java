package contract.quantity;

import static contract.conditions.NotNull.requireNotNull;

public class Range<T extends Number> {
    public final T start;
    public final T end;

    Range(final T start, final T end) {
        requireNotNull(start);
        requireNotNull(end);

        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + "," + end + "]";
    }
}
