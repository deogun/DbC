package contract.matcher;

import org.apache.commons.validator.routines.EmailValidator;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsValidEmailAddressMatcher {
    public static Matcher isValid() {
        return new TypeSafeMatcher<String>() {
            @Override
            protected boolean matchesSafely(final String value) {
                return EmailValidator.getInstance().isValid(value);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("IsValidEmailAddressMatcher");
            }
        };
    }
}
