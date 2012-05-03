package contract.conditions;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static contract.conditions.CollectionCondition.EVALUATION.CONTAINS_ELEMENT;
import static contract.conditions.CollectionCondition.EVALUATION.NOT_EMPTY;
import static contract.conditions.CollectionCondition.EVALUATION.NO_NULL_ELEMENTS;
import static contract.conditions.NotNull.requireNotNull;
import static java.util.Arrays.asList;

public class CollectionCondition implements Condition<Collection> {
    enum EVALUATION {NOT_EMPTY, NO_NULL_ELEMENTS, CONTAINS_ELEMENT}

    private static final Map<EVALUATION, EvaluationAction> evaluationActions;
    static {
        evaluationActions = new HashMap<EVALUATION, EvaluationAction>();
        evaluationActions.put(NOT_EMPTY, notEmptyAction());
        evaluationActions.put(NO_NULL_ELEMENTS, noNullElementsAction());
        evaluationActions.put(CONTAINS_ELEMENT, containsElementAction());
    }

    private final Collection collection;
    private final EvaluationAction evaluationAction;

    private CollectionCondition(final Collection collection, final EVALUATION evaluation) {
        this(collection, evaluationActions.get(evaluation));
    }

    private CollectionCondition(final Object value, final Collection collection, final EVALUATION evaluation) {
        this(removeAllElementsBut(value, collection), evaluationActions.get(evaluation));
    }

    private CollectionCondition(final Collection collection, final EvaluationAction evaluationAction) {
        requireNotNull(collection);
        requireNotNull(evaluationAction);

        this.evaluationAction = evaluationAction;
        this.collection = collection;
    }

    public static Condition<Collection> notEmpty(final Collection value) {
        requireNotNull(value);
        return new CollectionCondition(value, NOT_EMPTY);
    }

    public static Condition<Collection> noNullElements(final Collection value) {
        requireNotNull(value);
        return new CollectionCondition(value, NO_NULL_ELEMENTS);
    }

    public static Condition<Collection> containsElement(final Object value, final Collection collection) {
        requireNotNull(value);
        requireNotNull(collection);
        return new CollectionCondition(value, collection, CONTAINS_ELEMENT);
    }

    @Override
    public Collection evaluate() {
        return evaluationAction.execute(collection);
    }

    private static Collection removeAllElementsBut(final Object value, final Collection collection) {
        final Collection retainedElements = new LinkedList();

        assert retainedElements.addAll(collection) : "Copy of elements failed";

        retainedElements.retainAll(asList(value));

        return retainedElements;
    }

    private static EvaluationAction containsElementAction() {
        return new EvaluationAction() {
            @Override
            public Collection execute(final Collection collection) {
                assert !collection.isEmpty() : "Expected value not found in collection";
                return collection;
            }
        };
    }

    private static interface EvaluationAction {
        Collection execute(final Collection collection);
    }

    private static EvaluationAction notEmptyAction() {
        return new EvaluationAction() {
            @Override
            public Collection execute(final Collection collection) {
                assert !collection.isEmpty() : "Unexpected empty collection";
                return collection;
            }
        };
    }

    private static EvaluationAction noNullElementsAction() {
        return new EvaluationAction() {
            @Override
            public Collection execute(final Collection collection) {
                for(final Object element : collection) {
                    requireNotNull(element, "Unexpected NULL element found in collection");
                }
                return collection;
            }
        };
    }
}
