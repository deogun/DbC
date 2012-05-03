package contract.quantity;

public class Quantity {
    public static Range<Number> range(final Number start, final Number end) {
        return new Range<Number>(start, end);
    }
}
