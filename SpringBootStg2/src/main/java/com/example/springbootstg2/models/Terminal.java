package com.example.springbootstg2.models;

import jakarta.persistence.*;

import java.util.Random;
import jakarta.persistence.*;
import java.util.Random;
@Entity
@Table(name = "Terminal")
public class Terminal {
    public enum Designiation {
        RORO,
        Polyvalant,
        CONTENEUR
    }
    @Id
    private String code;
    @Enumerated(EnumType.STRING) // Stores the enum's name as a string in the DB
    @Column(nullable = false)
    private Designiation designation;

    public Terminal() {
        this.code = generateRandomCode();
    }


    public String getCode() {
        return code;
    }
    public static String generateRandomCode() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(3);
        for (int i = 0; i < 3; i++) {
            sb.append(letters.charAt(random.nextInt(letters.length())));
        }
        return sb.toString();
    }

    public Designiation getDesignation() {
        return designation;
    }

    public void setDesignation(Designiation designation) {
        this.designation = designation;
    }
}
