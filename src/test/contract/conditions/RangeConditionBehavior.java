package contract.conditions;

import contract.quantity.Quantity;
import contract.quantity.Range;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static contract.conditions.Evaluator.evaluate;
import static contract.conditions.RangeCondition.valueInRange;
import static junit.framework.Assert.fail;

@RunWith(Theories.class)
public class RangeConditionBehavior {
    @DataPoint
    public static final Range<Number> range1 = Quantity.range(0, 999);
    @DataPoint
    public static final Range<Number> range2 = Quantity.range(100.0, 100.0);
    @DataPoint
    public static final Range<Number> range3 = Quantity.range(-100.1, 100);

    @Theory
    public void shouldBeInRange(final Range<Number> range) {
        evaluate(valueInRange(100, range), "Value 100 within range ".concat(range.toString()).concat(" should be accepted"));
        evaluate(valueInRange(100.0, range), "Value 100.0 within range ".concat(range.toString()).concat(" should be accepted"));
    }

    @Theory
    public void shouldNotBeInRange(final Range<Number> range) {
        try {
            evaluate(valueInRange(1000, range), "Value 1000 should not be accepted in range ".concat(range.toString()));
        }
        catch (AssertionError e) {
           return;
        }
        fail("Unexpected success for 1000 in range ".concat(range.toString()));
    }
}
