package com.kevinpernia.inventory_system.domain.shared.exceptions;

public class ValidationException extends DomainException {
    public ValidationException(String message) {
        super(message);
    }
}