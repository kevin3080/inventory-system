package com.kevinpernia.inventory_system.domain.models.entities;

import com.kevinpernia.inventory_system.domain.shared.exceptions.ValidationException;
import com.kevinpernia.inventory_system.domain.shared.validations.ProductValidator;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Product {
  private final UUID id;
  private String name;
  private Float unitPrice;

  public Product(UUID id, String name, Float unitPrice) {
    ProductValidator.validate(id, name, unitPrice);

    this.id = id;
    this.name = name.trim().toLowerCase();
    this.unitPrice = unitPrice;
  }

  public void rename(String newName) {
    if (newName == null || newName.isBlank()) {
      throw new ValidationException("newName cannot be empty");
    }

    this.name = newName;
  }

  public void changeUnitPrice(Float newUnitPrice) {
    if (newUnitPrice < 0) {
      throw new ValidationException("newUnitPrice must be >= 0");
    }

    this.unitPrice = newUnitPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Product product)) return false;
    return id.equals(product.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
