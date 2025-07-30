package com.example.springbootstg2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "agentMaritime")
public class AgentMaritime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    // Ajout des champs pour l'authentification
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role = Role.AGENT; // Par défaut, Role.AGENT

    @OneToMany(mappedBy = "agentMaritime", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("agentMaritime")
    private List<Navire> navires;
    @OneToMany(mappedBy = "agent")
    private List<AvisDarrive> avisDarrives = new ArrayList<>();

    public AgentMaritime() {
        this.name = "";
        this.username = "";
        this.password = "";
        this.email = "";

    }
    public AgentMaritime(String name, String username, String password, String email, Role role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Navire> getNavires() {
        return navires;
    }

    public void setNavires(List<Navire> navires) {
        this.navires = navires;
    }

}
