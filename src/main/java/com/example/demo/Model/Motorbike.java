package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Motorbike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{moto.mark.mandatory}")
    private String brand;

    @NotBlank(message = "{moto.model.mandatory}")
    private String model;

    @NotNull(message = "{moto.year.mandatory}")
    private Integer year;

    @NotNull(message = "{moto.mileage.mandatory}")
    private Integer mileage;

    @OneToMany(mappedBy = "motorbike")
    private List<InstalledComponent> installedComponents;

    @OneToMany(mappedBy = "motorbike")
    private List<MaintenanceHistory> history;
}
