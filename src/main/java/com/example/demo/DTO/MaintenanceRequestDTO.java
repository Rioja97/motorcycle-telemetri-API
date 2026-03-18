package com.example.demo.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MaintenanceRequestDTO {

    private Long installedComponentId;
    private LocalDate maintenanceDate;
    private Integer currentKm;
    private String description;
}
