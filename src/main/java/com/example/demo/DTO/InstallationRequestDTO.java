package com.example.demo.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InstallationRequestDTO {

    private Long motorbikeId;
    private Long catalogComponentId;
    private Integer installationMileage;
    private LocalDate installationDate;

}

