package com.example.demo.Controller;

import com.example.demo.Model.CatalogComponent;
import com.example.demo.Service.CatalogComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/catalog-components")
@RequiredArgsConstructor
public class CatalogComponentController {

    private final CatalogComponentService catalogComponentService;

    @GetMapping
    public ResponseEntity<List<CatalogComponent>> getAll(){
        return ResponseEntity.ok(catalogComponentService.findAll());
    }
}
