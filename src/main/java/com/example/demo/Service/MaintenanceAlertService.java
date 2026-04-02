package com.example.demo.Service;

import com.example.demo.DTO.ComponentHealthResponseDTO;
import com.example.demo.Model.Alert;
import com.example.demo.Repository.AlertRepository;
import com.example.demo.Repository.MotorbikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MaintenanceAlertService {

    private final MotorbikeRepository motorbikeRepository;
    private final AlertRepository alertRepository;
    private final InstalledComponentService installedComponentService;
    private final EmailService emailService;

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
                    Alert alert = new Alert();

                    if (status.equals("WARNING") || status.equals("REPLACE")) {
                        alert.setMotorbike(motorbike);
                        alert.setComponentName(component.getCatalogComponent().getName());
                        alert.setStatus("WARNING");
                        alert.setMessage("ALERT: " + motorbike.getBrand() + " - " + motorbike.getModel() + " - " + component.getCatalogComponent().getName() + " expired");
                        alertRepository.save(alert);

                        String subject = "Alerta de Mantenimiento: " + motorbike.getBrand() + " " + motorbike.getModel();
                        String body = "Hola,\n\nTe informamos que tu moto requiere atención:\n" +
                                "Componente: " + component.getCatalogComponent().getName() + "\n" +
                                "Estado actual: " + status + "\n" +
                                "Kilometraje actual: " + motorbike.getCurrentKm() + " km\n\n" +
                                "Por favor, revisá tu unidad pronto para evitar daños mayores.";

                        emailService.sendAlertEmail("emma2eme@gmail.com", subject, body);
                    }
                });
        });
    }
}
