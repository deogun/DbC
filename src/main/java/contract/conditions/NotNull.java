package contract.conditions;

public class NotNull {
    public static <T>T requireNotNull(final T value) {
        return requireNotNull(value, "Unexpected null value");
    }

    public static <T>T requireNotNull(final T value, final String message) {
        assert value != null : message;
        return value;
    }

    public static <T>T ensureNotNull(final T value) {
        return requireNotNull(value);
    }

    public static <T>T ensureNotNull(final T value, final String message) {
        return requireNotNull(value, message);
    }
}
