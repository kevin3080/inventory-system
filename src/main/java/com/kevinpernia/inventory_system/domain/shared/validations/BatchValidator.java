package com.kevinpernia.inventory_system.domain.shared.validations;

import com.kevinpernia.inventory_system.application.dtos.RegisterBatchRequest;
import com.kevinpernia.inventory_system.domain.shared.Result;
import com.kevinpernia.inventory_system.domain.shared.exceptions.ValidationException;
import java.time.LocalDate;

public class BatchValidator {

  private BatchValidator() {}

  public static Result<Void> validate(RegisterBatchRequest request) {
    if (request.getProductId() == null) {
      return Result.failure(new ValidationException("Product id is required"));
    }

    if (request.getQuantity() <= 0) {
      return Result.failure(new ValidationException("Quantity must be greater than 0"));
    }

    if (request.getBatchNumber() == null || request.getBatchNumber().isBlank()) {
      return Result.failure(new ValidationException("Batch number is required"));
    }

    if (request.getDateOfManufacture() != null
        && request.getDateOfManufacture().isAfter(LocalDate.now())) {
      return Result.failure(
          new ValidationException("The manufacture date cannot be in the future."));
    }

    if (request.getDateOfExpiration() != null
        && request.getDateOfExpiration().isBefore(LocalDate.now())) {
      return Result.failure(new ValidationException("The expiration date cannot be in the past."));
    }

    return Result.success(null);
  }
}
