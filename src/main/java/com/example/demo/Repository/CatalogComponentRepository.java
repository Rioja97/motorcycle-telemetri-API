package com.example.demo.Repository;

import com.example.demo.Model.CatalogComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CatalogComponentRepository extends JpaRepository<CatalogComponent,Long> {
}
