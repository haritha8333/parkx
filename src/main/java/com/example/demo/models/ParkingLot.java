package com.example.demo.models;

import lombok.Data;

@Data
public class ParkingLot {
    String lotId;
    String name;
    String address;
    Integer totalSlots;
    Integer freeSlots;
}
