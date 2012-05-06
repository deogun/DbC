package contract.execution;

public class NeverGetHere {
    public static void neverGetHere() {
        neverGetHere("");
    }

    public static void neverGetHere(final String message) {
        assert false : "Unexpected execution branch. ".concat(message);
    }
}
