package contract.matcher;

import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static contract.Contract.ensureThat;
import static contract.Contract.requireThat;
import static contract.matcher.ValidEmailAddress.isValidEmailAddress;
import static org.hamcrest.Matchers.not;

@RunWith(Theories.class)
public class ValidEmailAddressBehavior {

    @Theory
    public void shouldBeValidAddress(@ValidAddress final String address) {
        requireThat(address).satisfies(isValidEmailAddress());
        ensureThat(address).satisfies(isValidEmailAddress());
    }

    @Theory
    public void shouldBeInvalidAddress(@InvalidAddress final String address) {
        requireThat(address).satisfies(not(isValidEmailAddress()));
        ensureThat(address).satisfies(not(isValidEmailAddress()));
    }
}
