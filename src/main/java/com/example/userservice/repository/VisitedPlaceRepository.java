package com.example.userservice.repository;


import com.example.userservice.entity.VisitedPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitedPlaceRepository extends JpaRepository<VisitedPlace, Long> {
    List<VisitedPlace> findByUserId(Long userId);
}