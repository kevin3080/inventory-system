package com.kevinpernia.inventory_system.infrastructure.persistence.entities;

import com.kevinpernia.inventory_system.domain.models.entities.Product;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {

  @Id private UUID id;
  private String name;
  private Float unitPrice;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<BatchEntity> batches = new ArrayList<>();

  public static ProductEntity fromDomain(Product product) {
    ProductEntity entity = new ProductEntity();
    entity.setId(product.getId());
    entity.setName(product.getName());
    entity.setUnitPrice(product.getUnitPrice());
    return entity;
  }

  public Product toDomain() {
    return new Product(id, name, unitPrice);
  }
}
