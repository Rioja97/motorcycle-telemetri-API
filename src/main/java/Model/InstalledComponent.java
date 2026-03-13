package Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

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
    @JoinColumn(name = "motorbyke_id")
    private Motorbike motorbike;

    @ManyToOne
    @JoinColumn(name = "catalog_compoent_id")
    private CatalogComponent catalogComponent;

    @OneToMany(mappedBy = "installedComponent")
    private List<MaintenanceHistory> maintenanceHistory;
}
