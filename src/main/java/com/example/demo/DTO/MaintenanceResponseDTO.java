package com.example.demo.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MaintenanceResponseDTO {

    private Long id;
    private String motorbikeInfo;
    private String componentName;
    private LocalDate maintenanceDate;
    private Integer currentKm;
    private String description;
}
