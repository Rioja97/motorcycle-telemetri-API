package com.example.demo.Service;

import com.example.demo.DTO.ComponentHealthResponseDTO;
import com.example.demo.Repository.MotorbikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MaintenanceAlertService {

    private final MotorbikeRepository motorbikeRepository;
    private final InstalledComponentService installedComponentService;

    @Transactional
    @Scheduled(cron = "*/10 * * * * *")
    public void newAlert (){
        motorbikeRepository.findAll().forEach(motorbike-> {
            Integer currentKm = motorbike.getCurrentKm();

            if (currentKm == null || motorbike.getInstalledComponents() == null) {
                return;
            }

            motorbike.getInstalledComponents()
                .stream()
                .filter(c -> c.getActive() != null && c.getActive())
                .forEach(component -> {
                    ComponentHealthResponseDTO healt = installedComponentService.calculateHealth(component, currentKm);
                    String status = healt.getStatus();

                    if (status.equals("WARNING") || status.equals("REPLACE")) {
                        System.out.println("ALERT: " + motorbike.getBrand() + " - " + motorbike.getModel() + " - " + component.getCatalogComponent().getName() + " expired");
                    }
                });
        });
    }
}
