package com.kevinpernia.inventory_system.application.services;

import com.kevinpernia.inventory_system.application.dtos.BatchResponse;
import com.kevinpernia.inventory_system.application.dtos.ProductWithBatchesResponse;
import com.kevinpernia.inventory_system.application.dtos.RegisterBatchRequest;
import com.kevinpernia.inventory_system.domain.models.entities.Batch;
import com.kevinpernia.inventory_system.domain.models.entities.Product;
import com.kevinpernia.inventory_system.domain.repositories.IBatchRepository;
import com.kevinpernia.inventory_system.domain.repositories.IProductRepository;
import com.kevinpernia.inventory_system.domain.shared.Result;
import com.kevinpernia.inventory_system.domain.shared.exceptions.ValidationException;
import com.kevinpernia.inventory_system.domain.shared.validations.BatchValidator;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
  private final IBatchRepository batchRepository;
  private final IProductRepository productRepository;

  public BatchService(IBatchRepository batchRepository, IProductRepository productRepository) {
    this.batchRepository = batchRepository;
    this.productRepository = productRepository;
  }

  public Result<Void> registerBatch(RegisterBatchRequest request) {

    Result<Void> validationResult = BatchValidator.validate(request);

    if (!validationResult.isSuccess()) {
      return validationResult;
    }

    if (productRepository.findById(request.getProductId()).isEmpty()) {
      return Result.failure(
          new ValidationException(
              "Product with ID " + request.getProductId() + " does not exist."));
    }

    Batch batch =
        new Batch(
            UUID.randomUUID(),
            request.getBatchNumber(),
            request.getProductId(),
            request.getQuantity(),
            request.getDateOfManufacture(),
            request.getDateOfExpiration(),
            LocalDate.now());

    batchRepository.save(batch);

    return Result.success(null);
  }

  public List<ProductWithBatchesResponse> listInventoryDetailed() {
    List<Product> products = productRepository.findAll();

    return products.stream()
        .map(
            product -> {
              List<Batch> batches = batchRepository.findByProductId(product.getId());

              int totalQuantity = batches.stream().mapToInt(Batch::getQuantity).sum();

              List<BatchResponse> batchResponses =
                  batches.stream()
                      .map(
                          b ->
                              new BatchResponse(
                                  b.getBatchNumber(),
                                  b.getQuantity(),
                                  b.getDateOfManufacture(),
                                  b.getDateOfExpiration()))
                      .toList();

              return new ProductWithBatchesResponse(
                  product.getId(),
                  product.getName(),
                  product.getUnitPrice(),
                  totalQuantity,
                  batchResponses);
            })
        .toList();
  }
}
