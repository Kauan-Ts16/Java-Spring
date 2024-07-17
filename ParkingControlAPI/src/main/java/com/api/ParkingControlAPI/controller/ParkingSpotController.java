package com.api.ParkingControlAPI.controller;

import com.api.ParkingControlAPI.dto.post.ParkingSpotPost;
import com.api.ParkingControlAPI.dto.put.ParkingSpotPut;
import com.api.ParkingControlAPI.model.ParkingSpotModel;
import com.api.ParkingControlAPI.service.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    @Autowired
    ParkingSpotService parkingSpotService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ParkingSpotModel> save(@RequestBody @Valid ParkingSpotPost parkingSpotPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotPost));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<ParkingSpotModel> update(@RequestBody @Valid ParkingSpotPut parkingSpotPut) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.update(parkingSpotPut));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") UUID id) {
        parkingSpotService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpotModel> getOne(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<ParkingSpotModel>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
    }

}
