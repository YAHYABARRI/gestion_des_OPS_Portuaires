package com.example.springbootstg2.controllers;

import com.example.springbootstg2.exception.ResourceNotFoundException;
import com.example.springbootstg2.models.Port;
import com.example.springbootstg2.repository.PortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class PortController {
    @Autowired
    private PortRepository portRepository;
    @PreAuthorize("hasAnyRole('AGENT', 'ADMIN')")
    @GetMapping("/ports")
    public List<Port> getAllPorts() {
        return portRepository.findAll();
    }
    @PostMapping("/ports")
    public Port createPort(@RequestBody Port portRequest) {

        Port port = new Port();


        port.setCapacity(portRequest.getCapacity());
        port.setdesignation(portRequest.getdesignation());


        return portRepository.save(port);
    }
    @GetMapping("/ports/{code}")
    public ResponseEntity<Port> getPort(@PathVariable String code) {
       Port port =  portRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Port not found"));
        return ResponseEntity.ok(port);
    }
    @PutMapping("/ports/{code}")
    public ResponseEntity<Port> updatePort(@PathVariable String code ,@RequestBody  Port port){
        Port port1 = portRepository.findById(code)
                .orElseThrow(()->new ResourceNotFoundException("Port not found"));

            port1.setdesignation(port.getdesignation());
            port1.setCapacity(port.getCapacity());
         Port  portUpdated =  portRepository.save(port1);
         return ResponseEntity.ok(portUpdated);

    }

    @DeleteMapping("/ports/{code}")
    public ResponseEntity<Map<String,Boolean>> deletePort(@PathVariable String code) {
        Port p1 = portRepository.findById(code).orElseThrow(()->new ResourceNotFoundException("Port not found"));
        portRepository.delete(p1);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
