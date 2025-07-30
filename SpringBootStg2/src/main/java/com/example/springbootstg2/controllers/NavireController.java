package com.example.springbootstg2.controllers;

import com.example.springbootstg2.exception.ResourceNotFoundException;
import com.example.springbootstg2.models.Navire;
import com.example.springbootstg2.repository.NavireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class NavireController {
    @Autowired
    NavireRepository navireRepository;
    @GetMapping("navires")
    public List<Navire> getNavires() {
        return navireRepository.findAll();
    }
    @DeleteMapping("navires/{numLloyd}")
    public ResponseEntity<Map<String,Boolean>> deleteNavire(@PathVariable String numLloyd) {
        Navire p1 = navireRepository.findById(numLloyd).orElseThrow(()->new ResourceNotFoundException("Navire not found"));
        navireRepository.delete(p1);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    @PutMapping("navires/{numLloyd}")
    public ResponseEntity<Navire> updateNavire(@PathVariable String numLloyd, @RequestBody Navire p1) {
        Navire p2 = navireRepository.findById(numLloyd).orElseThrow(()->new ResourceNotFoundException("Navire not found"));
        p2.setName(p1.getName());
        p2.setLargeur(p1.getLargeur());
        p2.setLongueur(p1.getLongueur());
        p2.setAgentMaritime(p1.getAgentMaritime());
        p2.setTirantEauMax(p1.getTirantEauMax());
         Navire p3=navireRepository.save(p2);
         return ResponseEntity.ok(p3);



    }
    @GetMapping("navires/{numLloyd}")
    public ResponseEntity<Navire> getNavire(@PathVariable String numLloyd) {
        Navire N1 = navireRepository.findById(numLloyd).orElseThrow(()->new ResourceNotFoundException("Navire not found"));
        return ResponseEntity.ok(N1);
    }
    @PostMapping("/navires")
    public Navire createNavires(@RequestBody Navire navireRequest) {

            Navire navire = new Navire();


        navire.setName(navireRequest.getName());
        navire.setTirantEauMax(navireRequest.getTirantEauMax());
        navire.setLargeur(navireRequest.getLargeur());
        navire.setLongueur(navireRequest.getLongueur());
        navire.setAgentMaritime(navireRequest.getAgentMaritime());




        return navireRepository.save(navire);
    }
    

}
