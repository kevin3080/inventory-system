package com.kevinpernia.inventory_system.domain.models.entities;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Batch {
  private UUID id;
  private UUID productId;
  private String batchNumber;
  private LocalDate dateOfManufacture;
  private LocalDate dateOfExpiration;
  private int quantity;
  private LocalDate dateOfRegistration;

  public Batch(
      UUID id,
      String batchNumber,
      UUID productId,
      int quantity,
      LocalDate dateOfManufacture,
      LocalDate dateOfExpiration,
      LocalDate dateOfRegistration) {
    this.id = id;
    this.batchNumber = batchNumber;
    this.productId = productId;
    this.dateOfManufacture = dateOfManufacture;
    this.dateOfExpiration = dateOfExpiration;
    this.quantity = quantity;
    this.dateOfRegistration = dateOfRegistration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Batch batch)) return false;
    return id.equals(batch.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public boolean isExpired(LocalDate today) {
    return dateOfExpiration.isBefore(today);
  }

  public boolean isLowStock(int threshold) {
    return quantity < threshold;
  }
}
