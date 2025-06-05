package com.kevinpernia.inventory_system.infrastructure.persistence.entities;

import com.kevinpernia.inventory_system.domain.models.entities.Batch;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "batches")
@Getter
@Setter
@NoArgsConstructor
public class BatchEntity {
  @Id private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private ProductEntity product;

  @Column(nullable = false)
  private String batchNumber;

  private LocalDate dateOfManufacture;
  private LocalDate dateOfExpiration;
  private int quantity;
  private LocalDate dateOfRegistration;

  public Batch toDomain() {
    return new Batch(
        id,
        batchNumber,
        product.getId(),
        quantity,
        dateOfManufacture,
        dateOfExpiration,
        dateOfRegistration);
  }

  public static BatchEntity fromDomain(Batch batch, ProductEntity productEntity) {
    BatchEntity entity = new BatchEntity();
    entity.setId(batch.getId());
    entity.setProduct(productEntity);
    entity.setBatchNumber(batch.getBatchNumber());
    entity.setDateOfManufacture(batch.getDateOfManufacture());
    entity.setDateOfExpiration(batch.getDateOfExpiration());
    entity.setQuantity(batch.getQuantity());
    return entity;
  }
}
