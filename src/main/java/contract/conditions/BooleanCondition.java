package contract.conditions;

class BooleanCondition implements Condition<Boolean> {

    private final Boolean value;
    private final boolean expectedEvaluationResult;

    BooleanCondition(final Boolean value, final boolean expectedEvaluationResult) {
        this.value = value;
        this.expectedEvaluationResult = expectedEvaluationResult;
    }

    @Override
    public Boolean evaluate() {
        assert value == expectedEvaluationResult : "Expected value to be " + expectedEvaluationResult + " but was " + (!expectedEvaluationResult);
        return value;
    }
}

