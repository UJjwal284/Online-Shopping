package com.example.onlineshopping.Inventory;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    final
    InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productId}")
    int getStockQuantity(@PathVariable Long productId) {
        return inventoryService.getStockQuantityByproductId(productId);
    }

    @PutMapping("/{productId}/{newQuantity}")
    void updateStockQuantity(@PathVariable Long productId, @PathVariable int newQuantity) {
        inventoryService.updateStockQuantity(productId, newQuantity);
    }
}