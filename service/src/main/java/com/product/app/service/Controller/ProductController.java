package com.product.app.service.Controller;

import com.product.app.service.Models.Product;
import com.product.app.service.Service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Product APIs", description = "API for handling product")
@RestController
@RequestMapping("/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    // Create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdOrder = productService.createProduct(product);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    // Get an order by product ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getOrderById(@PathVariable Long productId) {
        Optional<Product> order = productService.getProductById(productId);
        if (order.isPresent()) {
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all orders
    @GetMapping
    public List<Product> getAllOrders() {
        return productService.getAllProduct();
    }

    // Update an existing order
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateOrder(@PathVariable Long productId, @RequestBody Product productDetails) {
        Product updatedOrder = productService.updateProduct(productId, productDetails);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    // Delete an order
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long productId) {
        productService.deleteOrder(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
