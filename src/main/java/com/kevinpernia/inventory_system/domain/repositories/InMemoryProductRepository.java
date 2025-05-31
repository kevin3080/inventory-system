package com.kevinpernia.inventory_system.domain.repositories;

import com.kevinpernia.inventory_system.domain.models.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class InMemoryProductRepository implements IProductRepository {

    private final Map<UUID, Product> db = new HashMap<>();

    @Override
    public void add(Product product) {
        db.put(product.getId(), product);
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(db.values());
    }


    public Optional<Product> findByName(String name) {return db.values().stream().filter(p -> p.getName().equals(name)).findFirst();}
}