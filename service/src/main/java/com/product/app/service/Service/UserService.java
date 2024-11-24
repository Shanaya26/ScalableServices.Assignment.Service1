package com.product.app.service.Service;

import com.product.app.service.DTO.UserDTO;
import com.product.app.service.Models.Product;
import com.product.app.service.Models.Users;
import com.product.app.service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean register(Users user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return false;
        }
        user.setPassword(encryptPassword(user.getPassword())); // Encrypt password before saving
        userRepository.save(user);
        return true;
    }

    public boolean login(String username, String password) {
        Optional<Users> user = userRepository.findByUsername(username);
        return user.isPresent() && user.get().getPassword().equals(encryptPassword(password));
    }

    private String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public List<UserDTO> getAllUsers() {
        List<Users> users = userRepository.findAll();
        if (users.isEmpty()) {
            return new ArrayList<>(); // return an empty list if no users found
        }
        List<UserDTO> userDTOs = new ArrayList<>();
        for (Users user : users) {
            UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getEmail());
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }
}
