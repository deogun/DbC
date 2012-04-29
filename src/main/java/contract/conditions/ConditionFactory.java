package contract.conditions;

import java.util.Collection;

import static contract.Contract.require;
import static contract.conditions.NotNull.*;
import static contract.conditions.CollectionCondition.EVALUATION.*;

public class ConditionFactory {

    public static Condition<Boolean> isTrue(final Boolean value) {
        requireNotNull(value);
        return new BooleanCondition(value, true);
    }

    public static Condition<Boolean> isFalse(final Boolean value) {
        requireNotNull(value);
        return new BooleanCondition(value, false);
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
}
