package org.myboulderlog.shared.exception;

/**
 * Wrapper exception that gets thrown when Objectify get() returns too many results
 */
public class TooManyResultsException extends Exception {

    public TooManyResultsException(String msg) {
        super(msg);
    }

}