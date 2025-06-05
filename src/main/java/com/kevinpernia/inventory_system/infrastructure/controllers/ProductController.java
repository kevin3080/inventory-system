package com.kevinpernia.inventory_system.infrastructure.controllers;

import com.kevinpernia.inventory_system.application.dtos.ProductResponse;
import com.kevinpernia.inventory_system.application.dtos.RegisterProductRequest;
import com.kevinpernia.inventory_system.application.services.ProductService;
import com.kevinpernia.inventory_system.domain.shared.Result;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<Void> registerProduct(@RequestBody RegisterProductRequest request) {
    Result<Void> result = productService.registerProduct(request);

    if (!result.isSuccess() && result instanceof Result.Failure<Void> failure) {
      throw failure.getError();
    }

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<List<ProductResponse>> listProducts() {
    return ResponseEntity.ok(productService.listRegisterProducts());
  }
}
