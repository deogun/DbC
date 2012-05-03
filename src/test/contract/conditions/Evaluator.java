package contract.conditions;

import static junit.framework.Assert.fail;

class Evaluator {
    public static void evaluate(final Condition condition, final String failureMessage) {
        try {
            condition.evaluate();
        } catch (final AssertionError e) {
            fail("Unexpected assertion error. ".concat(failureMessage));
        }
    }

    public static void evaluateFailingCondition(final Condition... conditions) {
        for(final Condition condition : conditions) {
            try {
                condition.evaluate();
            }
            catch (final AssertionError e) {
                continue;
            }
            fail("Unexpected success for " + condition.getClass().getSimpleName());
        }
    }
}
