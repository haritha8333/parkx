package com.example.demo.repository;

import com.example.demo.models.ParkingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingSessionRepository extends JpaRepository<ParkingSession, String> {
    Optional<ParkingSession> findByVehicleNumberAndActiveTrue(String vechileNuber);
    Optional<ParkingSession> findBySessionIdAndActiveTrue(String sessionId);
}
