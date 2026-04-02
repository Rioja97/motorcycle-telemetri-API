package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorbike_id")
    private Motorbike motorbike;

    private String componentName;
    private String status;
    private String message;

    @Column(columnDefinition = "boolean default false")
    private Boolean isRead = false;

    private LocalDateTime createdAt = LocalDateTime.now();
}