package contract.conditions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static contract.conditions.ConditionFactory.noNullElements;
import static contract.conditions.ConditionFactory.notEmpty;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static junit.framework.Assert.fail;

public class CollectionConditionBehavior {
    @Test
    public void shouldAcceptNonEmptyList() {
        evaluate(notEmpty(asList("some element")), "A nonempty list should be accepted by notEmpty condition");
    }

    @Test
    public void shouldVerifyThatNoNullElementsExist() {
        final List<String> elements = new ArrayList<String>();
        elements.add("1");
        elements.add("2");

        evaluate(noNullElements(elements), "A list without null elements should be accepted");
    }

    @Test
    public void shouldFail() {
        for(final Condition condition : failingConditions()) {
           try {
               condition.evaluate();
           }
           catch (AssertionError e) {
               continue;
           }
           fail("Unexpected success for " + condition.getClass().getSimpleName());
        }
    }

    private List<Condition> failingConditions() {
        final List<Condition> conditions = new LinkedList<Condition>();

        conditions.add(notEmpty(EMPTY_LIST));
        conditions.add(noNullElements(listWithNullElementes()));

        return conditions;
    }

    private Collection listWithNullElementes() {
        final List<String> data = new ArrayList<String>();
        data.add("1");
        data.add(null);
        data.add("3");
        data.add(null);
        data.add("5");
        return data;
    }

    private void evaluate(final Condition condition, final String failureMessage) {
        try {
            condition.evaluate();
        } catch (AssertionError e) {
            fail("Unexpected assertion error. ".concat(failureMessage));
        }
    }
}
