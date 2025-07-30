package com.example.springbootstg2.repository;

import com.example.springbootstg2.models.AgentMaritime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<AgentMaritime, Long> {


    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Object> findByUsername(String username);
    Optional<AgentMaritime> findByEmail(String email);
}
