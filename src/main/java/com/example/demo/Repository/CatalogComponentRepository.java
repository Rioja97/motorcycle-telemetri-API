package com.example.demo.Repository;

import com.example.demo.Model.CatalogComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogComponentRepository extends JpaRepository<CatalogComponent,Long> {
}
