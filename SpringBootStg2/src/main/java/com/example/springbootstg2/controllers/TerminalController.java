package com.example.springbootstg2.controllers;


import com.example.springbootstg2.exception.ResourceNotFoundException;
import com.example.springbootstg2.models.Terminal;
import com.example.springbootstg2.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class TerminalController {
    @Autowired
    private TerminalRepository terminalRepository;
    @GetMapping("/terminaux")
    public List<Terminal> getAllTerminals() {
        return terminalRepository.findAll();
    }
    @PostMapping("/terminaux")
    public Terminal createTerminal(@RequestBody Terminal terminal){
        Terminal newTerminal = new Terminal();
        newTerminal.setDesignation(terminal.getDesignation());
        return terminalRepository.save(newTerminal);}
    @DeleteMapping("/terminaux/{code}")
        public ResponseEntity<Map<String,Boolean>> deleteTerminal(@PathVariable String code) {
        Terminal T1 = terminalRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Terminal with code " + code + " not found"));
        terminalRepository.delete(T1);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/terminaux/{code}")
    public ResponseEntity<Terminal> getTerminal(@PathVariable String code) {
        Terminal T1 = terminalRepository.findById(code).orElseThrow(() -> new ResourceNotFoundException("Terminal with code " + code + " not found"));
        return ResponseEntity.ok(T1);
    }
    @PutMapping("/terminaux/{code}")
    public ResponseEntity<Terminal> updateTerminal(@PathVariable String code, @RequestBody Terminal terminal) {
        Terminal T1= terminalRepository.findById(code).orElseThrow(() -> new ResourceNotFoundException("Terminal with code " + code + " not found"));
        T1.setDesignation(terminal.getDesignation());
        Terminal T2 = terminalRepository.save(T1);
        return ResponseEntity.ok(T2);
    }


}
