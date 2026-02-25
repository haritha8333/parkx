package com.example.demo.repository;

import com.example.demo.models.OccupancyStatus;
import com.example.demo.models.ParkingSlot;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot,String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM ParkingSlot s WHERE s.lotId=:lotId AND s.parkingStatus = :status")
    List<ParkingSlot> findByLotIdAndStatus(String lotId, OccupancyStatus status, Pageable pageable);


}
