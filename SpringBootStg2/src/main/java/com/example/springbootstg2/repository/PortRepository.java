package com.example.springbootstg2.repository;

import com.example.springbootstg2.models.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortRepository extends JpaRepository<Port, String> {

}
