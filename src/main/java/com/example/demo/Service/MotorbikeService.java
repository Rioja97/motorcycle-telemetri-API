package com.example.demo.Service;

import com.example.demo.DTO.ComponentHealthResponseDTO;
import com.example.demo.DTO.MotorbikeHealthReportDTO;
import com.example.demo.Model.InstalledComponent;
import com.example.demo.Model.Motorbike;
import com.example.demo.Repository.MotorbikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MotorbikeService {

    private final MotorbikeRepository motorbikeRepository;
    private final InstalledComponentService installedComponentService;

    public List<Motorbike> getAllMotorbikes(){
        return motorbikeRepository.findAll();
    }

    public Motorbike save(Motorbike motorbike){
        return motorbikeRepository.save(motorbike);
    }

    public MotorbikeHealthReportDTO getMotorbikeHealthReport(Long motorbikeId, Integer currentKm){
        Motorbike motorbike = motorbikeRepository.findById(motorbikeId)
                .orElseThrow(() -> new RuntimeException("Motorbike not found"));

        List<ComponentHealthResponseDTO> componentsHealth = motorbike.getInstalledComponents()
                .stream()
                .filter(c -> c.getActive() != null && c.getActive())
                .map(component -> installedComponentService.calculateHealth(component, currentKm))
                .toList();

        return MotorbikeHealthReportDTO.builder()
                .motorbikeInfo(motorbike.getBrand() + " " + motorbike.getModel())
                .currentKm(currentKm)
                .componentsHealth(componentsHealth)
                .build();
    }
}
