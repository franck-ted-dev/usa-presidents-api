package com.usapresidents.data.core.exception;

public class PresidentNotFoundException extends ResourceNotFoundException {
    public PresidentNotFoundException(Long id) {
        super("President with ID " + id + " was not found.");
    }
}
