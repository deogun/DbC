package contract.conditions;

import static contract.conditions.NotNull.requireNotNull;

public class BooleanCondition implements Condition<Boolean> {

    private final Boolean value;
    private final boolean expectedEvaluationResult;

    private BooleanCondition(final Boolean value, final boolean expectedEvaluationResult) {
        requireNotNull(value);
        this.value = value;
        this.expectedEvaluationResult = expectedEvaluationResult;
    }

    public static Condition<Boolean> isTrue(final Boolean value) {
        requireNotNull(value);
        return new BooleanCondition(value, true);
    }

    public static Condition<Boolean> isFalse(final Boolean value) {
        requireNotNull(value);
        return new BooleanCondition(value, false);
    }

    @Override
    public Boolean evaluate() {
        assert value == expectedEvaluationResult : "Expected value to be " + expectedEvaluationResult + " but was " + (!expectedEvaluationResult);
        return value;
    }
}

