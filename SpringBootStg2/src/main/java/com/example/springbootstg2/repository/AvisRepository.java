package com.example.springbootstg2.repository;

import com.example.springbootstg2.models.AvisDarrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvisRepository extends JpaRepository<AvisDarrive, Long> {
    @Query("SELECT a FROM AvisDarrive a WHERE a.agent.id = :agentId")
    List<AvisDarrive> findByAgentId(@Param("agentId") Long agentId);

}
