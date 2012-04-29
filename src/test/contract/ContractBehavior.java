package contract;

import contract.conditions.Condition;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.List;

import static contract.Contract.ensure;
import static contract.Contract.require;
import static contract.conditions.ConditionFactory.isTrue;
import static contract.conditions.NotNull.ensureNotNull;
import static contract.conditions.NotNull.requireNotNull;
import static java.util.Arrays.asList;
import static junit.framework.Assert.fail;

@RunWith(Theories.class)
public class ContractBehavior {

    @DataPoint
    public static final ValidCondition valid = new ValidCondition() {
        @Override
        public Condition condition() {
            return isTrue(1 == 1);
        }
    };

    @DataPoint
    public static final InvalidCondition invalid = new InvalidCondition() {
        @Override
        public Condition condition() {
            return null;
        }
    };

    @Theory
    public void shouldAcceptNonNullConditions(final ValidCondition valid) {
        require(valid.condition());
        ensure(valid.condition());
    }

    @Theory
    public void shouldNotBeAllowedToPassNullToRequireContract(final InvalidCondition invalid) {
        try {
            require(invalid.condition());
        } catch (AssertionError e) {
            return;
        }
        fail("Expected assertion error due to " + invalid.condition());
    }

    @Theory
    public void shouldNotBeAllowedToPassNullToEnsureContract(final InvalidCondition invalid) {
        try {
            ensure(invalid.condition());
        } catch (AssertionError e) {
            return;
        }
        fail("Expected assertion error due to " + invalid.condition());
    }

    @Test
    public void shouldBePossibleToUseContractsInAssignments() {
        final Boolean v1 = require(isTrue(1 == 1));
        final String v2 = ensureNotNull(new String("some string"));
        final Integer v3 = requireNotNull(new Integer(1));
        final List<String> v4 = ensureNotNull(asList("1","2"), "some message");
        final List<String> v5 = requireNotNull(asList("1","2"), "some message");
    }

    private interface ValidCondition<T> {
        Condition<T> condition();
    }

    private interface InvalidCondition {
        Condition condition();
    }
}
