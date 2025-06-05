package com.kevinpernia.inventory_system.infrastructure.persistence.repositories;

import com.kevinpernia.inventory_system.domain.models.entities.Product;
import com.kevinpernia.inventory_system.domain.repositories.IProductRepository;
import com.kevinpernia.inventory_system.infrastructure.persistence.entities.ProductEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements IProductRepository {

  private final ProductJpaRepository jpaRepository;

  @Override
  public void save(Product product) {
    jpaRepository.save(ProductEntity.fromDomain(product));
  }

  @Override
  public Optional<Product> findById(UUID id) {
    return jpaRepository.findById(id).map(ProductEntity::toDomain);
  }

  @Override
  public Optional<Product> findByName(String name) {
    return jpaRepository.findByName(name).map(ProductEntity::toDomain);
  }

  @Override
  public List<Product> findAll() {
    return jpaRepository.findAll().stream().map(ProductEntity::toDomain).toList();
  }
}
