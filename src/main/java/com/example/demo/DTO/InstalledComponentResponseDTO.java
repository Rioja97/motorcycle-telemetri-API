package com.example.demo.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class InstalledComponentResponseDTO {

    private Long id;
    private String motorbikeInfo;
    private String componentName;
    private Integer installationMileage;
    private LocalDate installationDate;
}
