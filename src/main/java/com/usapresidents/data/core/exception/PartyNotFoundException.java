package com.usapresidents.data.core.exception;

public class PartyNotFoundException extends ResourceNotFoundException {
    public PartyNotFoundException(String party) {
        super("Party '" + party + "' does not exist or has no registered presidents.");
    }
}
