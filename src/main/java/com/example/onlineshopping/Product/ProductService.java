package com.example.onlineshopping.Product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

//    public Product updateProduct(Long id, Product updatedProduct) {
//        return productRepository.updateById(id, updatedProduct);
//    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
