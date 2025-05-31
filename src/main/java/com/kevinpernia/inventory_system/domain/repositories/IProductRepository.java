package com.kevinpernia.inventory_system.domain.repositories;

import com.kevinpernia.inventory_system.domain.models.entities.Product;

import java.util.*;

public interface IProductRepository {
    void add(Product product);
    Optional<Product> findByName(String name);
    List<Product> getAll();
}