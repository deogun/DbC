package contract.conditions;

import java.util.Collection;

import static contract.conditions.CollectionCondition.EVALUATION.NOT_EMPTY;
import static contract.conditions.CollectionCondition.EVALUATION.NO_NULL_ELEMENTS;

class CollectionCondition implements Condition<Collection> {
    enum EVALUATION {NOT_EMPTY, NO_NULL_ELEMENTS}

    private final Collection collection;
    private final EVALUATION evaluation;

    public CollectionCondition(final Collection collection, final EVALUATION evaluation) {
        assert evaluation != null : "Evaluation type cannot be null";

        this.collection = collection;
        this.evaluation = evaluation;
    }

    @Override
    public Collection evaluate() {
        if(NOT_EMPTY.equals(evaluation)) {
            assert !collection.isEmpty() : "Unexpected empty collection";
        }
        else if(NO_NULL_ELEMENTS.equals(evaluation)) {
            for(final Object element : collection) {
                assert element != null : "Unexpected NULL element found in collection";
            }
        }

        return collection;
    }
}
