package contract.matcher;

import org.apache.commons.validator.routines.EmailValidator;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ValidEmailAddress {
    public static Matcher isValidEmailAddress() {
        return new TypeSafeMatcher<String>() {
            @Override
            protected boolean matchesSafely(final String value) {
                return EmailValidator.getInstance().isValid(value);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("email address");
            }
        };
    }
}
