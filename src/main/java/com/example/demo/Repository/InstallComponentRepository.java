package com.example.demo.Repository;

import com.example.demo.Model.InstalledComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstallComponentRepository extends JpaRepository<InstalledComponent, Long> {

    Optional<InstalledComponent> findByMotorbikeIdAndCatalogComponentIdAndActiveTrue(Long motorbikeId, Long catalogComponentId);
}
