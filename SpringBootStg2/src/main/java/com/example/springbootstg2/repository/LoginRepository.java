package com.jts.login.repo;

import java.util.Optional;

import com.example.springbootstg2.models.AgentMaritime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginRepository extends JpaRepository<AgentMaritime, Long> {

    Optional<AgentMaritime> findByUsername(String username);
}