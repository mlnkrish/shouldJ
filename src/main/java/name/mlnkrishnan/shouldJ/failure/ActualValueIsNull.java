package name.mlnkrishnan.shouldJ.failure;

public class ActualValueIsNull extends Error{
    public ActualValueIsNull() {
        super("cannot perform this assertion on a <null> value");
    }
}
