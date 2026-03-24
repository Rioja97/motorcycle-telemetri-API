package com.example.demo.Controller;

import com.example.demo.DTO.ComponentHealthResponseDTO;
import com.example.demo.DTO.InstallationRequestDTO;
import com.example.demo.DTO.InstalledComponentResponseDTO;
import com.example.demo.Service.InstalledComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/installed-components")
@RequiredArgsConstructor
public class InstalledComponentController {

    private final InstalledComponentService installedComponentService;

    @PostMapping
    public ResponseEntity<InstalledComponentResponseDTO> addInstalledComponent(@RequestBody InstallationRequestDTO installationRequestDTO) {
        InstalledComponentResponseDTO saveComponent = installedComponentService.installNewComponent(installationRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveComponent);
    }

    @GetMapping("/{id}/health")
    public ResponseEntity<ComponentHealthResponseDTO> checkHealth(
            @PathVariable Long id,
            @RequestParam Integer currentKm) {

        ComponentHealthResponseDTO response = installedComponentService.checkComponentHealth(id, currentKm);
        return ResponseEntity.ok(response);
    }
}
