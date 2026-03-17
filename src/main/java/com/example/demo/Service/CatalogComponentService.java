package com.example.demo.Service;

import com.example.demo.Model.CatalogComponent;
import com.example.demo.Repository.CatalogComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogComponentService {

    private final CatalogComponentRepository catalogComponentRepository;

    public List<CatalogComponent> findAll() {
        return catalogComponentRepository.findAll();
    }
}
