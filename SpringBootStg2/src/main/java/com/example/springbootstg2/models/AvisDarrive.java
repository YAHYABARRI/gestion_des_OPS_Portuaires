package com.example.springbootstg2.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "AvisDarrive")
public class AvisDarrive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateArrivee;

    @ManyToOne
    @JoinColumn(name = "port_depart_id")
    private Port portDeDepart;

    @ManyToOne
    @JoinColumn(name = "port_designation_id")
    private Port portDeDesignation;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private AgentMaritime agent;

    public AvisDarrive() {}

    public AvisDarrive(LocalDate dateArrivee, Port portDeDepart, Port portDeDesignation, AgentMaritime agent) {
        this.dateArrivee = dateArrivee;
        this.portDeDepart = portDeDepart;
        this.portDeDesignation = portDeDesignation;
        this.agent = agent;
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDateArrivee() { return dateArrivee; }
    public void setDateArrivee(LocalDate dateArrivee) { this.dateArrivee = dateArrivee; }

    public Port getPortDeDepart() { return portDeDepart; }
    public void setPortDeDepart(Port portDeDepart) { this.portDeDepart = portDeDepart; }

    public Port getPortDeDesignation() { return portDeDesignation; }
    public void setPortDeDesignation(Port portDeDesignation) { this.portDeDesignation = portDeDesignation; }

    public AgentMaritime getAgent() { return agent; }
    public void setAgent(AgentMaritime agent) { this.agent = agent; }
}
