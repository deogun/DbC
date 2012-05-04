package contract;


import com.sun.deploy.util.ArrayUtil;
import contract.conditions.Condition;
import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;

import static contract.conditions.NotNull.ensureNotNull;
import static contract.conditions.NotNull.requireNotNull;


public class Contract {
    public static <T>T require(final Condition<T>... conditions) {
        requireNotNull(conditions);
        return evaluate(conditions);
    }

    public static <T>T ensure(final Condition<T>... conditions) {
        return require(conditions);
    }

    private static <T>T evaluate(final Condition<T>... conditions) {
        T result = null;
        for(final Condition<T> condition : conditions) {
            result = requireNotNull(condition).evaluate();
        }
        return ensureNotNull(result);
    }
}
