package com.api.ParkingControlAPI.controller;

import com.api.ParkingControlAPI.dto.post.UserPost;
import com.api.ParkingControlAPI.dto.put.UserPut;
import com.api.ParkingControlAPI.model.UserModel;
import com.api.ParkingControlAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/parking-control/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<UserModel> save(@RequestBody @Valid UserPost userPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userPost));
    }

    @PutMapping
    public ResponseEntity<UserModel> update(@RequestBody @Valid UserPut userPut) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userPut));
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<UserModel> updateAdmin(@PathVariable(value = "id")UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateAdmin(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id")UUID id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getOne(@PathVariable(value = "id")UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

}
