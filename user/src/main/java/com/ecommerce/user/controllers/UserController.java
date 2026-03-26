package com.ecommerce.user.controllers;

import com.ecommerce.user.dtos.UserRequest;
import com.ecommerce.user.dtos.UserResponse;
import com.ecommerce.user.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userService.addUser(userRequest);
        return ResponseEntity.ok().body("Successfully added user");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUsersById(@PathVariable String id) {
        log.info("Request received for user: {}", id);
        log.trace("this is trace level");
        log.debug("this is debug level");
        log.warn("this is warn level");
        log.error("this is error level");
        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody UserRequest updateUserRequest, @PathVariable String id) {
        boolean updated = userService.updateUser(id, updateUserRequest);

        if(updated) {
            return ResponseEntity.ok("User updated successfully");
        }

        return ResponseEntity.notFound().build();
    }
}
