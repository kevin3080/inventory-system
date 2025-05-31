package com.kevinpernia.inventory_system.domain.models.entities;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class Product {
    private final UUID id;
    private String name;
    private Float unitPrice;

    public Product(UUID id, String name, Float unitPrice) {
        if(id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if(unitPrice < 0) {
            throw new IllegalArgumentException("Unit price must be >= 0");
        }

        this.id = id;
        this.name = name.trim().toLowerCase();
        this.unitPrice = unitPrice;
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()){
            throw new IllegalArgumentException("newName cannot be empty");
        }

        this.name = newName;
    }

    public void changeUnitPrice(Float newUnitPrice) {
        if (newUnitPrice < 0){
            throw new IllegalArgumentException("newUnitPrice must be >= 0");
        }

        this.unitPrice = newUnitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}