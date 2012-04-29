package contract;


import contract.conditions.Condition;


public class Contract {
    public static <T>T require(final Condition<T> condition) {
        requireNotNull(condition);
        return condition.evaluate();
    }

    public static <T>T ensure(final Condition<T> condition) {
        return require(condition);
    }

    public static <T>T requireNotNull(final T value) {
        assert value != null : "Unexpected null value";
        return value;
    }

    public static <T>T ensureNotNull(final T value) {
        return requireNotNull(value);
    }
}
