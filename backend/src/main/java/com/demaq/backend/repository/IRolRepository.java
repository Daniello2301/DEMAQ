package com.demaq.backend.repository;

import com.demaq.backend.model.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long> {
    
}
