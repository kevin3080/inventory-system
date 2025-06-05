package com.kevinpernia.inventory_system.infrastructure.persistence.repositories;

import com.kevinpernia.inventory_system.domain.models.entities.Batch;
import com.kevinpernia.inventory_system.domain.repositories.IBatchRepository;
import com.kevinpernia.inventory_system.domain.shared.exceptions.ValidationException;
import com.kevinpernia.inventory_system.infrastructure.persistence.entities.BatchEntity;
import com.kevinpernia.inventory_system.infrastructure.persistence.entities.ProductEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BatchRepositoryImpl implements IBatchRepository {
  private final BatchJpaRepository batchJpaRepository;
  private final ProductJpaRepository productJpaRepository;

  @Override
  public void save(Batch batch) {
    ProductEntity product =
        productJpaRepository
            .findById(batch.getProductId())
            .orElseThrow(() -> new ValidationException("Product not found"));

    batchJpaRepository.save(BatchEntity.fromDomain(batch, product));
  }

  @Override
  public void update(Batch batch) {
    ProductEntity product =
        productJpaRepository
            .findById(batch.getProductId())
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));

    batchJpaRepository.save(BatchEntity.fromDomain(batch, product));
  }

  @Override
  public Optional<Batch> findById(UUID id) {
    return batchJpaRepository.findById(id).map(BatchEntity::toDomain);
  }

  @Override
  public Optional<Batch> findByBatchNumber(String batchNumber) {
    return batchJpaRepository.findByBatchNumber(batchNumber).map(BatchEntity::toDomain);
  }

  @Override
  public List<Batch> findByProductId(UUID productId) {
    return batchJpaRepository.findByProductId(productId).stream()
        .map(BatchEntity::toDomain)
        .toList();
  }
}
