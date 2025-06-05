package com.kevinpernia.inventory_system.domain.repositories;

import com.kevinpernia.inventory_system.domain.models.entities.Product;
import java.util.*;

public interface IProductRepository {
  void save(Product product);

  Optional<Product> findById(UUID id);

  Optional<Product> findByName(String name);

  List<Product> findAll();
}
