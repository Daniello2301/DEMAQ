package com.demaq.backend.repository;

import com.demaq.backend.model.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
}
