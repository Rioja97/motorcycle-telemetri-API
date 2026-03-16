package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class MaintenanceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{maintenance.date.mandatory}")
    private LocalDate maintenanceDate;

    @NotNull(message = "{maintenance.currentKm.mandatory}")
    private Integer currentKm;

    @NotBlank(message = "{maintenance.description.mandatory}")
    private String description;

    @ManyToOne
    @JoinColumn(name = "motorbike_id")
    private Motorbike motorbike;

    @ManyToOne
    @JoinColumn(name = "installed_component_id")
    private InstalledComponent installedComponent;
}
