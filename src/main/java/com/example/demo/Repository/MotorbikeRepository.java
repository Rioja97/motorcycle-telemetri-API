package com.example.demo.Repository;

import com.example.demo.Model.Motorbike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotorbikeRepository extends JpaRepository<Motorbike, Long> {
}
