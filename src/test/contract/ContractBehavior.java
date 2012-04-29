package contract;

import contract.conditions.Condition;
import contract.conditions.ConditionFactory;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static contract.Contract.*;
import static contract.conditions.ConditionFactory.isTrue;
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
    }

    private interface ValidCondition<T> {
        Condition<T> condition();
    }

    private interface InvalidCondition {
        Condition condition();
    }
}
