package com.kevinpernia.inventory_system.application.dtos;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
  private UUID id;
  private String name;
  private Float unitPrice;

  public ProductResponse(UUID id, String name, Float unitPrice) {
    this.id = id;
    this.name = name;
    this.unitPrice = unitPrice;
  }
}
