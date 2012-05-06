package contract.execution;

import org.junit.Test;

import static contract.execution.NeverGetHere.neverGetHere;

public class NeverGetHereBehavior {
    @Test(expected = AssertionError.class)
    public void shouldAbortExecution() {
        neverGetHere();
    }

    @Test(expected = AssertionError.class)
    public void shouldAbortExecutionWithMessage() {
        neverGetHere("some message");
    }
}
