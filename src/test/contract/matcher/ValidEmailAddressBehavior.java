package contract.matcher;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static contract.Contract.ensureThat;
import static contract.Contract.requireThat;
import static contract.matcher.ValidEmailAddress.isValidEmailAddress;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assume.assumeThat;

@RunWith(Theories.class)
public class ValidEmailAddressBehavior {
    private interface Address { String address(); }
    private interface ValidAddress extends Address {}
    private interface InvalidAddress extends Address {}

    @DataPoints
    public static Address[] testData() {
        return new Address[]{
                validAddress("someaddress@example.com"),
                validAddress("simplewith+symbol@example.com"),
                validAddress("quite.common@example.com"),
                validAddress("a.little.more.unusual@dept.example.com"),
                validAddress("'@[10.10.10.10]"),
                validAddress("!#$%&'*+-/=?^_`{}|~@example.org"),

                invalidAddress("Abc.example.com"),
                invalidAddress("Abc.@example.com"),
                invalidAddress("A@b@c@example.com"),
                invalidAddress("a\"b(c)d,e:f;g<h>i[j\\k]l@example.com"),
                invalidAddress("")
        };
    }

    @Theory
    public void shouldBeValidAddress(final Address email) {
        assumeThat(email, instanceOf(ValidAddress.class));

        requireThat(email.address()).satisfiesAllOf(isValidEmailAddress());
        ensureThat(email.address()).satisfiesAllOf(isValidEmailAddress());
    }

    @Theory
    public void shouldBeInvalidAddress(final Address email) {
        assumeThat(email, instanceOf(InvalidAddress.class));

        requireThat(email.address()).satisfiesAllOf(not(isValidEmailAddress()));
        ensureThat(email.address()).satisfiesAllOf(not(isValidEmailAddress()));
    }

    private static Address validAddress(final String address) {
        return new ValidAddress() {
            @Override
            public String address() {
                return address;
            }
        };
    }

    private static Address invalidAddress(final String address) {
        return new InvalidAddress() {
            @Override
            public String address() {
                return address;
            }
        };
    }

}
