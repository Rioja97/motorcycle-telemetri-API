package com.example.demo.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MotorbikeHealthReportDTO {
    private String motorbikeInfo;
    private Integer currentKm;
    private List<ComponentHealthResponseDTO> componentsHealth;
}
