package com.example.demo.Controller;

import com.example.demo.DTO.MotorbikeHealthReportDTO;
import com.example.demo.Model.Motorbike;
import com.example.demo.Service.MotorbikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motorbikes")
@RequiredArgsConstructor
public class MotorbikeController {

    private final MotorbikeService motorbikeService;

    @PostMapping
    public ResponseEntity<Motorbike> save(@RequestBody Motorbike motorbike){
        Motorbike savedMotorbike = motorbikeService.save(motorbike);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMotorbike);
    }

    @GetMapping
    public ResponseEntity<List<Motorbike>> findAll(){
        return ResponseEntity.ok(motorbikeService.getAllMotorbikes());
    }

    @GetMapping("/{id}/health-report")
    public ResponseEntity<MotorbikeHealthReportDTO> getHealthReport(
            @PathVariable Long id,
            @RequestParam Integer currentKm){
        MotorbikeHealthReportDTO report = motorbikeService.getMotorbikeHealthReport(id, currentKm);
        return ResponseEntity.ok(report);
    }
}
