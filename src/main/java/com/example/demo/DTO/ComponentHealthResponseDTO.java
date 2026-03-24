package com.example.demo.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComponentHealthResponseDTO {
    private String componentName;
    private Integer installedAtKm;
    private Integer lifespanKm;
    private Integer expirationKm;
    private Integer currentKm;
    private Integer remainingKm;
    private String status;
}
