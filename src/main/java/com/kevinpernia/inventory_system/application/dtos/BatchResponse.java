package com.kevinpernia.inventory_system.application.dtos;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BatchResponse {
  private String batchNumber;
  private int quantity;
  private LocalDate dateOfManufacture;
  private LocalDate dateOfExpiration;

  public BatchResponse(
      String batchNumber, int quantity, LocalDate dateOfManufacture, LocalDate dateOfExpiration) {
    this.batchNumber = batchNumber;
    this.quantity = quantity;
    this.dateOfManufacture = dateOfManufacture;
    this.dateOfExpiration = dateOfExpiration;
  }
}
