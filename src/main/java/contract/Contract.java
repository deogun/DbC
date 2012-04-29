package contract;


import contract.conditions.Condition;

import static contract.conditions.NotNull.requireNotNull;


public class Contract {
    public static <T>T require(final Condition<T> condition) {
        requireNotNull(condition);
        return condition.evaluate();
    }

    public static <T>T ensure(final Condition<T> condition) {
        return require(condition);
    }
}
