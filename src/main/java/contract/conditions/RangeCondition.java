package contract.conditions;

import contract.quantity.Range;

import static contract.conditions.NotNull.requireNotNull;

public class RangeCondition<T> implements Condition {
    private final T valueToBeReturned;
    private final Number value;
    private final Range<? extends Number> range;

    private RangeCondition(final T valueToBeReturned, final Number value, final Range<? extends Number> range) {
        this.value = value;
        this.valueToBeReturned = valueToBeReturned;
        this.range = range;
    }

    public static Condition<Integer> valueInRange(final Integer value, final Range<Number> range) {
        requireNotNull(value);
        requireNotNull(range);
        return new RangeCondition(value, value, range);
    }

    public static Condition<Double> valueInRange(final Double value, final Range<Number> range) {
        requireNotNull(value);
        requireNotNull(range);
        return new RangeCondition(value, value, range);
    }

    @Override
    public T evaluate() {
        assert Double.compare(value.doubleValue(), range.start.doubleValue()) >= 0 &&
               Double.compare(value.doubleValue(), range.end.doubleValue()) <= 0 : "Value " + value + " not in range " + range;
        return valueToBeReturned;
    }
}
