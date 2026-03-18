package com.example.demo.Service;

import com.example.demo.DTO.MaintenanceRequestDTO;
import com.example.demo.DTO.MaintenanceResponseDTO;
import com.example.demo.Model.InstalledComponent;
import com.example.demo.Model.MaintenanceHistory;
import com.example.demo.Repository.InstallComponentRepository;
import com.example.demo.Repository.MaintenanceHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaintenanceHistoryService {

    private final MaintenanceHistoryRepository maintenanceHistoryRepository;
    private final InstallComponentRepository installComponentRepository;

    public MaintenanceResponseDTO addMaintenanceHistory(MaintenanceRequestDTO maintenanceHistory){

        InstalledComponent component = installComponentRepository.findById(maintenanceHistory.getInstalledComponentId())
                .orElseThrow(()-> new RuntimeException("Installed component not found"));

        MaintenanceHistory maintenanceHistoryEntity = new MaintenanceHistory();
        maintenanceHistoryEntity.setInstalledComponent(component);
        maintenanceHistoryEntity.setMaintenanceDate(maintenanceHistory.getMaintenanceDate());
        maintenanceHistoryEntity.setDescription(maintenanceHistory.getDescription());
        maintenanceHistoryEntity.setCurrentKm(maintenanceHistory.getCurrentKm());

        maintenanceHistoryRepository.save(maintenanceHistoryEntity);
        return MaintenanceResponseDTO.builder()
                .id(maintenanceHistoryEntity.getId())
                .motorbikeInfo(component.getMotorbike().getBrand() +  " " + component.getMotorbike().getModel())
                .componentName(component.getCatalogComponent().getName())
                .maintenanceDate(maintenanceHistoryEntity.getMaintenanceDate())
                .currentKm(maintenanceHistoryEntity.getCurrentKm())
                .description(maintenanceHistoryEntity.getDescription())
                .build();
    }
}
