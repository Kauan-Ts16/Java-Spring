package com.api.ParkingControlAPI.controller;

import com.api.ParkingControlAPI.dto.post.ParkingSpotPost;
import com.api.ParkingControlAPI.dto.put.ParkingSpotPut;
import com.api.ParkingControlAPI.model.ParkingSpotModel;
import com.api.ParkingControlAPI.service.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-control/parking-spot")
public class ParkingSpotController {

    @Autowired
    ParkingSpotService parkingSpotService;


    @PostMapping
    public ResponseEntity<ParkingSpotModel> save(@RequestBody @Valid ParkingSpotPost parkingSpotPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotPost));
    }

    @PutMapping
    public ResponseEntity<ParkingSpotModel> update(@RequestBody @Valid ParkingSpotPut parkingSpotPut) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.update(parkingSpotPut));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") UUID id) {
        parkingSpotService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpotModel> getOne(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpotModel>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
    }

}
