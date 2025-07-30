package com.example.springbootstg2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Entity
@Table(name = "escale")
public class Escale {

    @Id
    private String id; // ex: "202507001"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEscale status = StatusEscale.ACTIVEE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "avis_id", nullable = false)
    @JsonIgnoreProperties("escales")
    private AvisDarrive avisDarrive;

    public Escale() {
        this.id = generateId();
        this.status = StatusEscale.ACTIVEE;
    }

    public Escale(AvisDarrive avisDarrive) {
        this();
        this.avisDarrive = avisDarrive;
    }

    private String generateId() {
        LocalDate now = LocalDate.now();
        String yearMonth = now.format(DateTimeFormatter.ofPattern("yyyyMM"));
        String seq = String.format("%03d", new Random().nextInt(999) + 1);
        return yearMonth + seq;
    }

    // Getters et setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StatusEscale getStatus() {
        return status;
    }

    public void setStatus(StatusEscale status) {
        this.status = status;
    }

    public AvisDarrive getAvisDarrive() {
        return avisDarrive;
    }

    public void setAvisDarrive(AvisDarrive avisDarrive) {
        this.avisDarrive = avisDarrive;
    }
}
