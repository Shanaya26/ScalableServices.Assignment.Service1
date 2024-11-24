package com.product.app.service.Controller;

import com.product.app.service.Models.Product;
import com.product.app.service.Models.Users;
import com.product.app.service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

//@Tag(name = "User APIs", description = "API for handling user")
//@RestController
//@RequestMapping(value = "/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user) {
        boolean isRegistered = userService.register(user);
        return isRegistered ? ResponseEntity.ok("Registration successful") : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
//        boolean isAuthenticated = userService.login(username, password);
//        return isAuthenticated ? ResponseEntity.ok("Login successful") : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }
}
