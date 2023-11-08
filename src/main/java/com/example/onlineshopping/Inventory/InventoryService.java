package com.example.onlineshopping.Inventory;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public int getStockQuantityByproductId(Long productId) {
        Inventory inventory = inventoryRepository.findByproductId(productId);
        return (inventory != null) ? inventory.getStockQuantity() : 0;
    }

    public void updateStockQuantity(Long productId, int newQuantity) {
        Inventory inventory = inventoryRepository.findByproductId(productId);
        if (inventory != null) {
            inventory.setStockQuantity(newQuantity);
            inventoryRepository.save(inventory);
        }
    }
}
