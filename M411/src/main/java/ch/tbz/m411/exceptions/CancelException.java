package ch.tbz.m411.exceptions;

public class CancelException extends Exception {

    public CancelException() {
        super("The action has been cancelled.");
    }

}
