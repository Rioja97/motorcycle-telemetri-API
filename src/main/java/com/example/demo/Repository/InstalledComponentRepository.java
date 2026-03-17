package com.example.demo.Repository;

import com.example.demo.Model.InstalledComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstalledComponentRepository extends JpaRepository<InstalledComponent, Long> {
}
