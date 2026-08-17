package com.example.userservice.controller;

import com.example.userservice.entity.Favorite;
import com.example.userservice.entity.User;
import com.example.userservice.entity.VisitedPlace;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // --- User Endpoints ---
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    // --- Favorites Endpoints ---
    @PostMapping("/{userId}/favorites")
    public ResponseEntity<Favorite> addFavorite(@PathVariable Long userId, @RequestParam String destinationId) {
        return ResponseEntity.ok(userService.addFavorite(userId, destinationId));
    }

    @GetMapping("/{userId}/favorites")
    public ResponseEntity<List<Favorite>> getUserFavorites(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserFavorites(userId));
    }

    @DeleteMapping("/favorites/{favoriteId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long favoriteId) {
        userService.removeFavorite(favoriteId);
        return ResponseEntity.ok().build();
    }

    // --- Visited Places Endpoints ---
    @PostMapping("/{userId}/visited")
    public ResponseEntity<VisitedPlace> addVisitedPlace(@PathVariable Long userId, @RequestBody VisitedPlace visitedPlace) {
        visitedPlace.setUserId(userId);
        return ResponseEntity.ok(userService.addVisitedPlace(visitedPlace));
    }

    @GetMapping("/{userId}/visited")
    public ResponseEntity<List<VisitedPlace>> getUserVisitedPlaces(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserVisitedPlaces(userId));
    }

    @DeleteMapping("/visited/{placeId}")
    public ResponseEntity<Void> removeVisitedPlace(@PathVariable Long placeId) {
        userService.removeVisitedPlace(placeId);
        return ResponseEntity.ok().build();
    }
}
