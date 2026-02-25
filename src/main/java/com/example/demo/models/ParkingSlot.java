package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ParkingSlot {
    @Id
    String slotId;
    String lotId;
    Integer floor;
    @Enumerated(EnumType.STRING)
    OccupancyStatus parkingStatus;
}
