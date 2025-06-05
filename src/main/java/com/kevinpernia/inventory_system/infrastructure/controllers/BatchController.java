package com.kevinpernia.inventory_system.infrastructure.controllers;

import com.kevinpernia.inventory_system.application.dtos.ProductWithBatchesResponse;
import com.kevinpernia.inventory_system.application.dtos.RegisterBatchRequest;
import com.kevinpernia.inventory_system.application.services.BatchService;
import com.kevinpernia.inventory_system.domain.shared.Result;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class BatchController {
  private final BatchService batchService;

  public BatchController(BatchService batchService) {
    this.batchService = batchService;
  }

  @PostMapping("/{productId}/batches")
  public ResponseEntity<Void> registerBatch(
      @PathVariable UUID productId, @RequestBody RegisterBatchRequest request) {
    request.setProductId(productId);
    Result<Void> result = batchService.registerBatch(request);

    if (!result.isSuccess() && result instanceof Result.Failure<Void> failure) {
      throw failure.getError();
    }

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/batches")
  public ResponseEntity<List<ProductWithBatchesResponse>> listProductsWithBatch() {
    return ResponseEntity.ok(batchService.listInventoryDetailed());
  }
}
