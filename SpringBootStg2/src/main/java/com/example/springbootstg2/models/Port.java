package com.example.springbootstg2.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "port")
public class Port {

    @Id
    private String code;
    @Column(name = "capacity")
    private double capacity;
    @Column(name = "designation")
    private String designation;

    public Port() {
        this.code = generateRandomCode();
        this.capacity = 0.0;
        this.designation = "";
    }

    public static String generateRandomCode() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            sb.append(letters.charAt(random.nextInt(letters.length())));
        }
        return sb.toString();
    }
    @OneToMany(mappedBy = "portDeDepart")
    private List<AvisDarrive> avisDepart = new ArrayList<>();

    @OneToMany(mappedBy = "portDeDesignation")
    private List<AvisDarrive> avisDesignation = new ArrayList<>();


    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getdesignation() {
        return designation;
    }

    public void setdesignation(String designation) {
        this.designation = designation;
    }
}
