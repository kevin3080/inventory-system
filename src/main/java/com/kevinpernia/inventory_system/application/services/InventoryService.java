package com.kevinpernia.inventory_system.application.services;

import com.kevinpernia.inventory_system.application.dtos.RegisterProductRequest;
import com.kevinpernia.inventory_system.domain.models.entities.Product;
import com.kevinpernia.inventory_system.domain.repositories.IProductRepository;
import com.kevinpernia.inventory_system.domain.shared.Result;
import com.kevinpernia.inventory_system.domain.shared.exceptions.ProductAlreadyExistsException;
import com.kevinpernia.inventory_system.domain.shared.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {
    private final IProductRepository productRepository;

    public InventoryService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Result<Void> registerProduct(RegisterProductRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            return Result.failure(new ValidationException("Name is required"));
        }

        Optional<Product> existing = productRepository.findByName(request.getName().trim().toLowerCase());
        if (existing.isPresent()) {
            return Result.failure(new ProductAlreadyExistsException(request.getName()));
        }

        Product product = new Product(UUID.randomUUID(), request.getName(), request.getUnitPrice());
        productRepository.add(product);

        return Result.success(null);
    }

    public List<Product> listInventory() {
        return productRepository.getAll();
    }
}