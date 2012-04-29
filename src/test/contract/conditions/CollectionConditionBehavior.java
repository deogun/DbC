package contract.conditions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static contract.conditions.ConditionFactory.*;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static junit.framework.Assert.fail;

public class CollectionConditionBehavior {
    @Test
    public void shouldAcceptNonEmptyList() {
        evaluate(notEmpty(asList("some element")), "A nonempty list should be accepted");
    }

    @Test
    public void shouldVerifyThatNoNullElementsExist() {
        evaluate(noNullElements(asList("1","2")), "A list without null elements should be accepted");
    }

    @Test
    public void shouldContainElement() {
        evaluate(containsElement(3, asList(1,2,3,4,5)), "An included element should be found.");
    }

    @Test
    public void shouldFail() {
        for(final Condition condition : failingConditions()) {
           try {
               condition.evaluate();
           }
           catch (final AssertionError e) {
               continue;
           }
           fail("Unexpected success for " + condition.getClass().getSimpleName());
        }
    }

    private List<Condition> failingConditions() {
        final List<Condition> conditions = new LinkedList<Condition>();

        conditions.add(notEmpty(EMPTY_LIST));
        conditions.add(noNullElements(listWithNullElements()));
        conditions.add(containsElement(3, asList(1,2,4,5)));

        return conditions;
    }

    private Collection listWithNullElements() {
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
        } catch (final AssertionError e) {
            fail("Unexpected assertion error. ".concat(failureMessage));
        }
    }
}
