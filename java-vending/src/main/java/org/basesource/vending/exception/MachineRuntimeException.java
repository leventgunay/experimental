package org.basesource.vending.exception;

public class MachineRuntimeException extends RuntimeException {

    public MachineRuntimeException(String exp) {
        super(exp);
    }

    public MachineRuntimeException(Exception root, String exp) {
        super(exp, root);
    }
}
