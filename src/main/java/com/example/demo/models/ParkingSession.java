package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class ParkingSession {
    @Id
    private String sessionId;
    private String vehicleNumber;

    private String lotId;
    @ManyToOne
    @JoinColumn(name = "slot_id")
    private ParkingSlot slot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private boolean active;
}
