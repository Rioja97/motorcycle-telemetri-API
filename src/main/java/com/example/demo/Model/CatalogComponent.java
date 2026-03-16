package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class CatalogComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{catalog.name.mandatory}")
    private String name;

    @NotNull(message = "{catalog.lifespanKm.mandatory}")
    private Integer lifespanKm;

    @OneToMany(mappedBy = "catalogComponent")
    private List<InstalledComponent> installedComponents;
}
