package com.kevinpernia.inventory_system.application.dtos;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterBatchRequest {
  private UUID productId;
  private String batchNumber;
  private LocalDate dateOfManufacture;
  private LocalDate dateOfExpiration;
  private int quantity;
}
