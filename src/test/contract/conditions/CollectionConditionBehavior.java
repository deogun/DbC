package contract.conditions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static contract.conditions.CollectionCondition.*;
import static contract.conditions.Evaluator.evaluate;
import static contract.conditions.Evaluator.evaluateFailingCondition;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;

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
    public void shouldBeFailingConditions() {
       evaluateFailingCondition(failingConditions());
    }

    private Condition[] failingConditions() {
        return new Condition[]{
                notEmpty(EMPTY_LIST),
                noNullElements(collectionWithNullElements()),
                containsElement(3, asList(1,2,4,5))};
    }

    private Collection collectionWithNullElements() {
        final List<String> data = new ArrayList<String>();
        data.add("1");
        data.add(null);
        data.add("3");
        data.add(null);
        data.add("5");
        return data;
    }
}
