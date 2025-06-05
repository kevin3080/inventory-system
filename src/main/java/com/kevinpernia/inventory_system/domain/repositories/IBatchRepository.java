package com.kevinpernia.inventory_system.domain.repositories;

import com.kevinpernia.inventory_system.domain.models.entities.Batch;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBatchRepository {
  void save(Batch batch);

  void update(Batch batch);

  Optional<Batch> findById(UUID id);

  Optional<Batch> findByBatchNumber(String code);

  List<Batch> findByProductId(UUID productId);
}
