package com.kevinpernia.inventory_system.domain.shared.exceptions;

public class DomainException  extends  RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}