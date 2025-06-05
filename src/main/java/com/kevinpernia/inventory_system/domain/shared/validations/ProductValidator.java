package com.kevinpernia.inventory_system.domain.shared.validations;

import com.kevinpernia.inventory_system.domain.shared.exceptions.ValidationException;
import java.util.UUID;

public class ProductValidator {

  private ProductValidator() {}

  public static void validate(UUID id, String name, Float unitPrice) {
    if (id == null) {
      throw new ValidationException("id cannot be null");
    }
    if (name == null || name.isBlank()) {
      throw new ValidationException("name cannot be null");
    }
    if (unitPrice < 0) {
      throw new ValidationException("Unit price must be >= 0");
    }
  }
}
