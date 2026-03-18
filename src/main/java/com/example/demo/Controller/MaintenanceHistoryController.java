package com.example.demo.Controller;

import com.example.demo.DTO.MaintenanceRequestDTO;
import com.example.demo.DTO.MaintenanceResponseDTO;
import com.example.demo.Service.MaintenanceHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/maintenance-history")
@RequiredArgsConstructor
public class MaintenanceHistoryController {

    private final MaintenanceHistoryService maintenanceHistoryService;

    @PostMapping
    public ResponseEntity<MaintenanceResponseDTO> addMaintenanceHistory(@RequestBody MaintenanceRequestDTO maintenanceRequestDTO) {
        MaintenanceResponseDTO maintenanceResponseDTO = maintenanceHistoryService.addMaintenanceHistory(maintenanceRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceResponseDTO);
    }
}
