package com.example.userservice.service;

import com.example.userservice.entity.Favorite;
import com.example.userservice.entity.User;
import com.example.userservice.entity.VisitedPlace;
import com.example.userservice.repository.FavoriteRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.repository.VisitedPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final VisitedPlaceRepository visitedPlaceRepository;

    // User Operations
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setName(userDetails.getName());
        user.setAvatarUrl(userDetails.getAvatarUrl());
        return userRepository.save(user);
    }

    // Favorite Operations
    public Favorite addFavorite(Long userId, String destinationId) {
        if(favoriteRepository.findByUserIdAndDestinationId(userId, destinationId).isPresent()){
            throw new RuntimeException("Already added to favorites");
        }
        Favorite favorite = Favorite.builder()
                .userId(userId)
                .destinationId(destinationId)
                .build();
        return favoriteRepository.save(favorite);
    }

    public List<Favorite> getUserFavorites(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public void removeFavorite(Long favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }

    // Visited Place Operations
    public VisitedPlace addVisitedPlace(VisitedPlace place) {
        return visitedPlaceRepository.save(place);
    }

    public List<VisitedPlace> getUserVisitedPlaces(Long userId) {
        return visitedPlaceRepository.findByUserId(userId);
    }

    public void removeVisitedPlace(Long placeId) {
        visitedPlaceRepository.deleteById(placeId);
    }
}
