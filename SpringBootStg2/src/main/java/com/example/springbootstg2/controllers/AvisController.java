package com.example.springbootstg2.controllers;

import com.example.springbootstg2.exception.ResourceNotFoundException;
import com.example.springbootstg2.models.*;
import com.example.springbootstg2.repository.AgentRepository;
import com.example.springbootstg2.repository.AvisRepository;
import com.example.springbootstg2.repository.EscaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
@RestController
public class AvisController {
    @Autowired
    AvisRepository avisRepository;
    @Autowired
    EscaleRepository escaleRepository;
    @Autowired
    private AgentRepository agentRepository;

    @GetMapping("avis")
    public List<AvisDarrive> getAvis() {
        return avisRepository.findAll();
    }
    @DeleteMapping("avis/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteAvis(@PathVariable Long id) {
        AvisDarrive p1 = avisRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("avis not found"));
        avisRepository.delete(p1);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    @PostMapping("avis")
    public AvisDarrive addAvis(@RequestBody AvisDarrive avis) {
        // Sauvegarder l'avis d'arrivée
        AvisDarrive savedAvis = avisRepository.save(avis);

        // Créer une nouvelle escale liée à l'avis
        Escale escale = new Escale();
        escale.setStatus(StatusEscale.PREVUE);
        escale.setAvisDarrive(savedAvis); // Relation objet, pas juste l'ID

        // Sauvegarder l'escale
        escaleRepository.save(escale);

        return savedAvis;
    }


}
