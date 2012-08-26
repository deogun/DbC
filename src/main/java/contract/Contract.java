package contract;


import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsNot.not;

/**
 * <p>
 * This class is the entry point for any contract. The idea is to define a domain specific language (DSL) where
 * the user may write expressive contracts such as
 *</p>
 * <li><i>
 * requireThat(collection).satisfies(not(nullValue()), not(empty()), not(hasItem(nullValue())));
 * </i></li>
 * <p>
 * In addition, each contract should return its actual value. That is by returning the same value that was used
 * in the contract, one may use contracts in assignments, e.g
 * </p>
 * <li><i>
 * final String value = requireThat(value).satisfies(not(nullValue()), not(isEmptyString()));
 * </i></li>
 * @param <T> type of parameter value
 */
public final class Contract<T> {
    private final T value;

    private Contract(final T value) {
        this.value = value;
    }

    public static <T>Contract<T> requireThat(final T value) {
        return new Contract<T>(value);
    }

    public static <T>Contract<T> ensureThat(final T value) {
        return new Contract<T>(value);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public T satisfies(final Matcher... matchers) {
        assertThat(value, allOf(matchers));
        return value;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public T satisfiesAnyOf(final Matcher... matchers) {
        assertThat(value, anyOf(matchers));
        return value;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public T satisfyNonOf(final Matcher... matchers) {
        assertThat(value, not(anyOf(matchers)));
        return value;
    }
}
