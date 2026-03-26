package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class InstalledComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{motorcycle.mileage.mandatory}")
    private Integer mileage;

    @NotNull(message = "{component.installationDate.mandatory}")
    private LocalDate installationDate;

    @ManyToOne
    @JoinColumn(name = "motorbike_id")
    private Motorbike motorbike;

    @ManyToOne
    @JoinColumn(name = "catalog_component_id")
    private CatalogComponent catalogComponent;

    @OneToMany(mappedBy = "installedComponent")
    private List<MaintenanceHistory> maintenanceHistory;

    @Column(columnDefinition = "boolean default true")
    private Boolean active = true;
}
