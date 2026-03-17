package com.example.demo.Repository;

import com.example.demo.Model.MaintenanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceHistoryRepository extends JpaRepository<MaintenanceHistory, Long> {
}
