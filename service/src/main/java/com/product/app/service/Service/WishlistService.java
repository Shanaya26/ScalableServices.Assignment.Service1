package com.product.app.service.Service;

import com.product.app.service.Models.Product;
import com.product.app.service.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class WishlistService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductRepository productRepository;

    public String addProductToWishlist(Long productId) {
        // Example: You would typically retrieve product details from a database or service
        Optional<Product> product = productRepository.findById(productId);

        // Construct the URL for the wishlist service endpoint
        // Base URL of the Wishlist Service (adjust as needed)
        String WISHLIST_SERVICE_URL = "http://localhost:8081/wishlist";
        String url = WISHLIST_SERVICE_URL + "/add";  // Assuming this endpoint exists in Wishlist Service

        // Send the HTTP POST request to Wishlist Service
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(product), Void.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "Product successfully added to the wishlist.";
        } else {
            return "Failed to add product to the wishlist.";
        }
    }
}
