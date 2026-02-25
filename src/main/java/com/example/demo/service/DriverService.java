package com.example.demo.service;

import com.example.demo.dto.EntryRequestDTO;
import com.example.demo.dto.EntryResponseDTO;
import com.example.demo.dto.ExitRequestDTO;
import com.example.demo.models.OccupancyStatus;
import com.example.demo.models.ParkingSession;
import com.example.demo.models.ParkingSlot;
import com.example.demo.repository.ParkingSessionRepository;
import com.example.demo.repository.ParkingSlotRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DriverService {
    @Autowired
    ParkingSlotRepository parkingSlotRepository;

    @Autowired
    ParkingSessionRepository parkingSessionRepository;

    @Transactional
    public EntryResponseDTO assignSlot(EntryRequestDTO  entryRequestDTO) {
        String parkingLotId=entryRequestDTO.getParkingLotId();
        String vehicleNumber=entryRequestDTO.getVechicleNumber();
        Optional<ParkingSession> existingSession =
                parkingSessionRepository
                        .findByVehicleNumberAndActiveTrue(vehicleNumber);
        if (existingSession.isPresent()) {
            ParkingSession session = existingSession.get();
            return new EntryResponseDTO(
                    session.getSlot().getSlotId(),
                    session.getSessionId(),
                    "Already assigned"
            );
        }

        List<ParkingSlot> slots=parkingSlotRepository
                .findByLotIdAndStatus(parkingLotId, OccupancyStatus.VACANT, PageRequest.of(0, 1));
        if(slots.isEmpty()){
            throw new RuntimeException("No slots available");
        }
        ParkingSlot slot = slots.get(0);
        slot.setParkingStatus(OccupancyStatus.OCCUPIED);
//no need .save
        String sessionId = UUID.randomUUID().toString();

        ParkingSession parkingSession = new ParkingSession();
        parkingSession.setSlot(slot);
        parkingSession.setVehicleNumber(vehicleNumber);
        parkingSession.setSessionId(sessionId);
        parkingSession.setLotId(entryRequestDTO.getParkingLotId());
        parkingSession.setEntryTime(LocalDateTime.now());
        parkingSession.setActive(true);


        parkingSessionRepository.save(parkingSession);

        return new EntryResponseDTO(slot.getSlotId(),sessionId, "Slot assigned successfully");
    }

    @Transactional
    public String exitSlot(ExitRequestDTO exitRequestDTO) {
        String sessionId = exitRequestDTO.getSessionId();
        Optional<ParkingSession> parkingSession=parkingSessionRepository.findBySessionIdAndActiveTrue(sessionId);
        if (parkingSession.isPresent()) {
            ParkingSession parkingSession1 = parkingSession.get();
            parkingSession1.setActive(false);
            parkingSession1.setExitTime(LocalDateTime.now());

            ParkingSlot slot = parkingSlotRepository
                    .findById(parkingSession1.getSlot().getSlotId())
                    .orElseThrow(() ->
                            new RuntimeException("Slot not found"));
            slot.setParkingStatus(OccupancyStatus.VACANT);


            parkingSessionRepository.save(parkingSession1);
            return "Vehicle can exit";
        }
        else  {
            throw new RuntimeException("Session not found");
        }

    }
}
