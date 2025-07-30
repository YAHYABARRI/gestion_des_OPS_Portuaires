package com.example.springbootstg2.controllers;

import com.example.springbootstg2.exception.ResourceNotFoundException;
import com.example.springbootstg2.models.AgentMaritime;
import com.example.springbootstg2.models.Navire;
import com.example.springbootstg2.repository.AgentRepository;
import org.aspectj.weaver.loadtime.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
@RestController

public class AgentController {
    @Autowired
    private AgentRepository agentRepository;
    @GetMapping("agents")
    public List<AgentMaritime> getAllAgents() {
        return agentRepository.findAll();
    }
    @DeleteMapping("agents/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteNavire(@PathVariable Long id) {
        AgentMaritime p1 = agentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("id not found"));
        agentRepository.delete(p1);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
