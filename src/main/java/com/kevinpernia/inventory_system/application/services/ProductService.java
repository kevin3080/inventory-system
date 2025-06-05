package com.kevinpernia.inventory_system.application.services;

import com.kevinpernia.inventory_system.application.dtos.ProductResponse;
import com.kevinpernia.inventory_system.application.dtos.RegisterProductRequest;
import com.kevinpernia.inventory_system.domain.models.entities.Product;
import com.kevinpernia.inventory_system.domain.repositories.IProductRepository;
import com.kevinpernia.inventory_system.domain.shared.Result;
import com.kevinpernia.inventory_system.domain.shared.exceptions.ProductAlreadyExistsException;
import com.kevinpernia.inventory_system.domain.shared.exceptions.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  private final IProductRepository productRepository;

  public ProductService(IProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Result<Void> registerProduct(RegisterProductRequest request) {
    if (request.getName() == null || request.getName().isBlank()) {
      return Result.failure(new ValidationException("Name is required"));
    }

    Optional<Product> existing =
        productRepository.findByName(request.getName().trim().toLowerCase());
    if (existing.isPresent()) {
      return Result.failure(new ProductAlreadyExistsException(request.getName()));
    }

    Product product = new Product(UUID.randomUUID(), request.getName(), request.getUnitPrice());
    productRepository.save(product);

    return Result.success(null);
  }

  public List<ProductResponse> listRegisterProducts() {
    List<Product> products = productRepository.findAll();
    return products.stream()
        .map(
            product ->
                new ProductResponse(product.getId(), product.getName(), product.getUnitPrice()))
        .toList();
  }
}
