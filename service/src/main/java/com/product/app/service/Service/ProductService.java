package com.product.app.service.Service;

import com.product.app.service.Models.Product;
import com.product.app.service.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // Create a new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Get an product by its product ID
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    // Get all product
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    // Update an existing product
    public Product updateProduct(Long productId, Product productDetails) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStockQuantity(productDetails.getStockQuantity());
        product.setImageUrl(productDetails.getImageUrl());

        // Updating timestamp, assume update timestamp is updated automatically by DB
        product.setUpdatedAt(productDetails.getUpdatedAt());

        return productRepository.save(product);
    }

    // Delete an order
    public void deleteOrder(Long productId) {
        Product order = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Order not found"));
        productRepository.delete(order);
    }
}
