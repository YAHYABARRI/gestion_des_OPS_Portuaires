package com.example.springbootstg2.repository;

import com.example.springbootstg2.models.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminalRepository extends JpaRepository<Terminal, String> {
}
