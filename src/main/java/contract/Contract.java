package contract;


import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsNot.not;


public final class Contract<T> {
    private final T actual;

    private Contract(final T actual) {
        this.actual = actual;
    }

    public static <T>Contract<T> requireThat(final T value) {
        return new Contract<T>(value);
    }

    public static <T>Contract<T> ensureThat(final T value) {
        return new Contract<T>(value);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public T satisfies(final Matcher... matchers) {
        assertThat(actual, allOf(matchers));
        return actual;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public T satisfiesAnyOf(final Matcher... matchers) {
        assertThat(actual, anyOf(matchers));
        return actual;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public T satisfyNonOf(final Matcher... matchers) {
        assertThat(actual, not(anyOf(matchers)));
        return actual;
    }
}
