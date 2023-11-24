package com.example.onlineshopping.Cart;

import com.example.onlineshopping.Product.Product;
import jakarta.persistence.*;

@Entity
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    private int quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return Math.round(this.product.getPrice() * this.quantity * 100.0) / 100.0;
    }
}