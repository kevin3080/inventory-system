package com.kevinpernia.inventory_system.application.dtos;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductWithBatchesResponse {
  private UUID id;
  private String name;
  private double unitPrice;
  private int totalQuantity;
  private List<BatchResponse> batches;

  public ProductWithBatchesResponse(
      UUID id,
      String name,
      Float unitPrice,
      int totalQuantity,
      List<BatchResponse> batchResponses) {
    this.id = id;
    this.name = name;
    this.unitPrice = unitPrice;
    this.totalQuantity = totalQuantity;
    this.batches = batchResponses;
  }
}
