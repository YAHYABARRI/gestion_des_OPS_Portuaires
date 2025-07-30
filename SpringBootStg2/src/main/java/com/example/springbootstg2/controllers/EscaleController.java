package com.example.springbootstg2.controllers;

import com.example.springbootstg2.exception.ResourceNotFoundException;
import com.example.springbootstg2.models.Escale;
import com.example.springbootstg2.models.Navire;
import com.example.springbootstg2.models.Port;
import com.example.springbootstg2.repository.EscaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
@RestController
public class EscaleController {
    @Autowired
    EscaleRepository escRepository;

    @GetMapping("/escales")
    public List<Escale> getAllEscales() {
        return escRepository.findAll();
    }

    @DeleteMapping("/escales/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePort(@PathVariable Long id) {
        Escale p1 = escRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found"));
        escRepository.delete(p1);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PutMapping("escales/{id}")
    public ResponseEntity<Escale> updateEscale(@PathVariable Long id, @RequestBody Escale e1) {
        Escale e2 = escRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found"));
        e2.setStatus(e1.getStatus());
        escRepository.save(e2);
        return ResponseEntity.ok(e2);
    }
    @GetMapping("escales/{id}")
    public ResponseEntity<Escale> getEscalebyId(@PathVariable Long id) {
        Escale E1 = escRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found"));
        return ResponseEntity.ok(E1);
    }
}