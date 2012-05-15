package contract;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static contract.Contract.ensureThat;
import static contract.Contract.requireThat;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsIn.isIn;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import static org.hamcrest.text.IsEmptyString.isEmptyString;

public class ContractBehavior {
    @Test
    public void shouldSatisfyAllOf() {
        requireThat(anyValidString()).satisfies(notNullValue(String.class), not(isEmptyString()));
        ensureThat(EMPTY_LIST).satisfies(notNullValue(Collection.class), empty());
    }

    @Test
    public void shouldSatisfyAnyOf() {
        requireThat(anyNonEmptyList()).satisfiesAnyOf(instanceOf(Double.class), not(empty()));
        ensureThat(anyNonEmptyList()).satisfiesAnyOf(instanceOf(Double.class), not(empty()));
    }

    @Test
    public void shouldProvideConvenienceMethodForReadability() {
        requireThat(anyValidString()).satisfies(not(instanceOf(Integer.class)));
        ensureThat(anyValidString()).satisfies(not(instanceOf(Integer.class)));
    }

    @Test
    public void shouldNotSatisfyAnyOf() {
        requireThat(new Double(1.3)).satisfyNonOf(isEmptyOrNullString(), isIn(asList(1.0,1.2,1.4)));
    }

    @Test
    public void shouldSupportCustomizedMatcher() {
        requireThat(new SomeCustomizedClass("42")).satisfies(customizedMatcher("42"));
    }

    @Test
    public void shouldSupportAssignment() {
        final Integer a = requireThat(new Integer(42)).satisfies(isIn(asList(1, 2, 42, 30)));
        final Integer b = ensureThat(new Integer(42)).satisfies(isIn(asList(1, 2, 42, 30)));
        final Integer c = ensureThat(new Integer(42)).satisfyNonOf(isIn(asList(1, 2, 30)), instanceOf(Double.class));
        final SomeCustomizedClass d = requireThat(new SomeCustomizedClass("x")).satisfies(customizedMatcher("x"));

        assertEquals(new Integer(42), a);
        assertEquals(new Integer(42), b);
        assertEquals(new Integer(42), c);
        assertEquals("x", d.value);
    }


    private String anyValidString() {
        return "some string";
    }

    private List<String> anyNonEmptyList() {
        return asList("a","b");
    }

    private Matcher customizedMatcher(final String expectedValue) {
        return new CustomizedMatcher(expectedValue);
    }

    private static final class CustomizedMatcher extends TypeSafeMatcher<SomeCustomizedClass> {

        private final String expectedValue;

        public CustomizedMatcher(final String expectedValue) {
            this.expectedValue = expectedValue;
        }

        @Override
        protected boolean matchesSafely(final SomeCustomizedClass item) {
            return expectedValue.equals(item.value);
        }

        @Override
        public void describeTo(final Description description) {
            description.appendText("CustomizedMatcher");
        }
    }

    private static final class SomeCustomizedClass {
        public final String value;

        public SomeCustomizedClass(final String value) {
            this.value = value;
        }
    }
}
