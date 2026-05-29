package com.usapresidents.data.core.exception;

public class StateNotFoundException extends ResourceNotFoundException {
    public StateNotFoundException(String stateBorn) {
        super("No presidents found who were born in the state: '" + stateBorn + "'.");
    }
}
