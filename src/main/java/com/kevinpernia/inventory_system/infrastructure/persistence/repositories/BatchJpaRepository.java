package com.kevinpernia.inventory_system.infrastructure.persistence.repositories;

import com.kevinpernia.inventory_system.infrastructure.persistence.entities.BatchEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchJpaRepository extends JpaRepository<BatchEntity, UUID> {
  Optional<BatchEntity> findByBatchNumber(String batchNumber);

  List<BatchEntity> findByProductId(UUID productId);
}
