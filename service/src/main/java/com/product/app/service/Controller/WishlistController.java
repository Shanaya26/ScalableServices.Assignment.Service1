package com.product.app.service.Controller;

import com.product.app.service.Models.Product;
import com.product.app.service.Service.WishlistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Wishlist APIs", description = "API for handling product")
@RestController
@RequestMapping("/v1/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;
    @GetMapping("/add/product/{productId}")
    public String getOrderById(@PathVariable Long productId) {
        return wishlistService.addProductToWishlist(productId);
    }
}
