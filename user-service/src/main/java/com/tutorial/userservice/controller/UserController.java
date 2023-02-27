package com.tutorial.userservice.controller;

import com.tutorial.userservice.entity.User;
import com.tutorial.userservice.model.Bike;
import com.tutorial.userservice.model.Car;
import com.tutorial.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping //muestra todos los usuarios
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(users);
        }
    }

    @GetMapping("/{id}") //busca un usuario
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

        @PostMapping //guarda un usuario
       public ResponseEntity<User> saveUser(@RequestBody User user){
            User userNew = userService.save(user);
            return ResponseEntity.ok(userNew);
        }

        @GetMapping("/cars/{userId}")//busca los cars del usuario
        public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId){

         User user = userService.getUserById(userId);
            if(user==null)
                return ResponseEntity.notFound().build();

                 List<Car> cars = userService.getCars(userId);

              return ResponseEntity.ok(cars);

        }

        @GetMapping("/bike/{userId}")// busca las bikes del usuario
        public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId){
           User user = userService.getUserById(userId);
            if(user == null){
                return ResponseEntity.notFound().build();
            }
            else{
                List<Bike> bikes = userService.getBikes(userId);
                return ResponseEntity.ok(bikes);

            }
        }

        @PostMapping("/savecar/{userId}") //guarda un car de un usuario
        public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car){
        User user = userService.getUserById(userId);
        if(user !=null) {
            Car carnew = userService.saveCar(userId, car);
            return ResponseEntity.ok(car);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/savebike/{userId}") //guarda una bike de un usuario
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike){
        User user = userService.getUserById(userId);
        if(user !=null) {
            Bike bikeNew = userService.saveBike(userId, bike);
            return ResponseEntity.ok(bikeNew);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVechicles(@PathVariable("userId") int userId){
        Map<String, Object> result = userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(result);
    }

}

