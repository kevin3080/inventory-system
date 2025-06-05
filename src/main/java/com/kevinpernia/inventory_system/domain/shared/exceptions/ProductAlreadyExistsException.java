package com.kevinpernia.inventory_system.domain.shared.exceptions;

public class ProductAlreadyExistsException extends DomainException {
  public ProductAlreadyExistsException(String name) {
    super("Product with name " + name + " already exists");
  }
}
