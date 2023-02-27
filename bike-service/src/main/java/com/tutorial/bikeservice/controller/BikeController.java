package com.tutorial.bikeservice.controller;

import com.tutorial.bikeservice.service.BikeService;
import com.tutorial.bikeservice.entity.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/bike")

public class BikeController {

    @Autowired
    BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll(){
        List<Bike> bikes = bikeService.getAll();
        if(bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(bikes);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBike(@PathVariable("id") int id) {
        Bike bike = bikeService.getBikeById(id);
        if (bike == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(bike);
        }
    }
    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId) {
        List<Bike> bikes = bikeService.byUserId(userId);
            return ResponseEntity.ok(bikes);

    }

    @PostMapping///
    public ResponseEntity<Bike> saveBike(@RequestBody Bike bike){
        Bike bikeNew = bikeService.save(bike);
        return ResponseEntity.ok(bikeNew);
    }

}