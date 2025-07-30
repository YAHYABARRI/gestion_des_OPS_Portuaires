package com.example.springbootstg2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name="navire")
public class Navire {

    @Id
    private String NumLloyd ;
    private String name;
    private float longueur ;
    private float tirantEauMax ;
    private float largeur ;


    public Navire(String name, float longueur, float tirantEauMax, float largeur,AgentMaritime agentMaritime) {
        this.name = name;
        this.longueur = longueur;
        this.tirantEauMax = tirantEauMax;
        this.largeur = largeur;
        this.agentMaritime = agentMaritime;

    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agent_maritime_id")
    @JsonIgnoreProperties("navire")
    private AgentMaritime agentMaritime;

    public Navire() {
        this.NumLloyd = generateRandomCode();
        this.name = "";
        this.longueur = 0;
        this.tirantEauMax = 0;
        this.largeur = 0;
        this.agentMaritime = new AgentMaritime();
    }
    public static String generateRandomCode() {
        String letters = "1234567890";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(7);
        for (int i = 0; i < 7; i++) {
            sb.append(letters.charAt(random.nextInt(letters.length())));
        }
        return sb.toString();
    }

    public String getNumLloyd() {
        return NumLloyd;
    }

    public AgentMaritime getAgentMaritime() {
        return agentMaritime;
    }

    public void setAgentMaritime(AgentMaritime agentMaritime) {
        this.agentMaritime = agentMaritime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLongueur() {
        return longueur;
    }

    public void setLongueur(float longueur) {
        this.longueur = longueur;
    }

    public float getTirantEauMax() {
        return tirantEauMax;
    }

    public void setTirantEauMax(float tirantEauMax) {
        this.tirantEauMax = tirantEauMax;
    }

    public float getLargeur() {
        return largeur;
    }

    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }
}
