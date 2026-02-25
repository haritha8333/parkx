package com.example.demo.controller;

import com.example.demo.dto.ParkingLotDTO;
import com.example.demo.dto.ParkingLotSummaryDTO;
import com.example.demo.dto.SlotDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @PostMapping("/parking-lots")
    public ResponseEntity<ParkingLotDTO> createLot(@RequestBody ParkingLotDTO parkingLotDTO) {
        return new ResponseEntity<>(parkingLotDTO, HttpStatus.OK);
    }

    @PostMapping("/parking-lots/{lotId}/slots")
    public ResponseEntity<SlotDTO> addSlot(@RequestBody SlotDTO slotDTO, @PathVariable String lotId) {
        return new ResponseEntity<>(slotDTO, HttpStatus.OK);
    }

    @PatchMapping("/slots/{slotId}")
    public void updateSlotStatus(@RequestBody SlotDTO slotDTO, @PathVariable String slotId) {

    }

    @GetMapping("/parking-lots/{lotId}/summary")
    public ResponseEntity<ParkingLotSummaryDTO> getSummary(@PathVariable String lotId) {
        return new ResponseEntity<>(new ParkingLotSummaryDTO(), HttpStatus.OK);
    }
}
