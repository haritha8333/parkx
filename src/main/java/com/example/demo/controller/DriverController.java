package com.example.demo.controller;

import com.example.demo.dto.EntryRequestDTO;
import com.example.demo.dto.EntryResponseDTO;
import com.example.demo.dto.ExitRequestDTO;
import com.example.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parking")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PostMapping("/entry")
    public ResponseEntity<EntryResponseDTO> assignSlot(@RequestBody EntryRequestDTO entryRequestDTO) {
        EntryResponseDTO entryResponseDTO = driverService.assignSlot(entryRequestDTO) ;
        return new ResponseEntity<>(entryResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/exit")
    public ResponseEntity<String> exit(@RequestBody ExitRequestDTO exitRequestDTO) {

        return new ResponseEntity<>(driverService.exitSlot(exitRequestDTO),HttpStatus.OK);
    }
}
