package contract.conditions;

import org.junit.Test;

import static contract.conditions.ConditionFactory.isFalse;
import static contract.conditions.ConditionFactory.isTrue;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class BooleanConditionBehavior {
    @Test
    public void shouldEvaluateToTrue() {
        assertTrue(isTrue(true).evaluate());
    }

    @Test
    public void shouldEvaluateToFalse() {
        assertFalse(isFalse(false).evaluate());
    }
}
