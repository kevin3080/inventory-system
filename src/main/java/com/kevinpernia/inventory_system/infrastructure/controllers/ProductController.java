package com.kevinpernia.inventory_system.infrastructure.controllers;

import com.kevinpernia.inventory_system.application.dtos.RegisterProductRequest;
import com.kevinpernia.inventory_system.application.services.InventoryService;
import com.kevinpernia.inventory_system.domain.models.entities.Product;
import com.kevinpernia.inventory_system.domain.shared.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final InventoryService inventoryService;

    public ProductController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<Void> registerProduct(@RequestBody RegisterProductRequest request) {
        Result<Void> result = inventoryService.registerProduct(request);

        if(!result.isSuccess() && result instanceof Result.Failure<Void> failure){
            throw failure.getError();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public  ResponseEntity<List<Product>> listProducts() {
        return ResponseEntity.ok(inventoryService.listInventory());
    }

}