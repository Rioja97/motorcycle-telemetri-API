package com.example.demo.Service;

import com.example.demo.DTO.ComponentHealthResponseDTO;
import com.example.demo.DTO.InstallationRequestDTO;
import com.example.demo.DTO.InstalledComponentResponseDTO;
import com.example.demo.Model.CatalogComponent;
import com.example.demo.Model.InstalledComponent;
import com.example.demo.Model.Motorbike;
import com.example.demo.Repository.CatalogComponentRepository;
import com.example.demo.Repository.InstallComponentRepository;
import com.example.demo.Repository.MotorbikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstalledComponentService {

    private final InstallComponentRepository installedComponentRepository;
    private final MotorbikeRepository motorbikeRepository;
    private final CatalogComponentRepository catalogComponentRepository;

    public InstalledComponentResponseDTO installNewComponent(InstallationRequestDTO installationRequestDTO) {

        Motorbike motorbike = motorbikeRepository.findById(installationRequestDTO.getMotorbikeId()).orElseThrow(() -> new RuntimeException("Motorbike not found"));
        CatalogComponent catalogComponent = catalogComponentRepository.findById(installationRequestDTO.getCatalogComponentId()).orElseThrow(() -> new RuntimeException("Catalog component not found"));

        installedComponentRepository.findByMotorbikeIdAndCatalogComponentIdAndActiveTrue(motorbike.getId(), catalogComponent.getId())
                .ifPresent(oldComponent -> {
                    oldComponent.setActive(false);
                    installedComponentRepository.save(oldComponent);
                });

        InstalledComponent installComponent = new InstalledComponent();
        installComponent.setMotorbike(motorbike);
        installComponent.setCatalogComponent(catalogComponent);
        installComponent.setMileage(installationRequestDTO.getInstallationMileage());
        installComponent.setInstallationDate(installationRequestDTO.getInstallationDate());

        InstalledComponent savedComponent = installedComponentRepository.save(installComponent);

        return InstalledComponentResponseDTO.builder()
                .id(savedComponent.getId())
                .motorbikeInfo(motorbike.getBrand() + " " + motorbike.getModel())
                .componentName(savedComponent.getCatalogComponent().getName())
                .installationMileage(savedComponent.getMileage())
                .installationDate(savedComponent.getInstallationDate())
                .build();
    }

    public ComponentHealthResponseDTO calculateHealth(InstalledComponent component, Integer currentKm) {
        Integer lifespanKm = component.getCatalogComponent().getLifespanKm();
        Integer installedAtKm = component.getMileage();
        Integer expirationKm = installedAtKm + lifespanKm;
        Integer remainingKm = expirationKm - currentKm;

        String status;
        if (remainingKm < 0) {
            status = "REPLACE";
        } else if (remainingKm <= 500) {
            status = "WARNING";
        } else {
            status = "OK";
        }

        return ComponentHealthResponseDTO.builder()
                .componentName(component.getCatalogComponent().getName())
                .installedAtKm(installedAtKm)
                .lifespanKm(lifespanKm)
                .expirationKm(expirationKm)
                .currentKm(currentKm)
                .remainingKm(remainingKm)
                .status(status)
                .build();
    }

    public ComponentHealthResponseDTO checkComponentHealth(Long installedComponentId, Integer currentKm) {
        InstalledComponent component = installedComponentRepository.findById(installedComponentId)
                .orElseThrow(() -> new RuntimeException("Component not found"));

        return calculateHealth(component, currentKm);
    }
}
