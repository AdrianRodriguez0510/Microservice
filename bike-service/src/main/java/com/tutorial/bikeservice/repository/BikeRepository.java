package com.tutorial.bikeservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tutorial.bikeservice.entity.Bike;
import java.util.List;

public interface BikeRepository extends JpaRepository<Bike, Integer> {

    List<Bike> findByUserId(int UserId);
}
