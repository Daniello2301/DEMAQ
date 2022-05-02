package com.demaq.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demaq.backend.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
}
