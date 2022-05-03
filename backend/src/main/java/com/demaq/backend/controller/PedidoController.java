package com.demaq.backend.controller;

import java.util.List;

import com.demaq.backend.dto.PedidoDto;
import com.demaq.backend.exception.RestException;
import com.demaq.backend.service.iface.IPedidoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    private static final Logger LOG = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    private IPedidoService pedidoService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK )
    public ResponseEntity<Object> getAll() throws RestException
    {

        LOG.info("Get All Pedidos");

        List<PedidoDto> response = pedidoService.getAll();

        return ResponseEntity.ok().body(response) ;
    }



    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> getById(@PathVariable Long id) throws RestException
    {
        PedidoDto response = pedidoService.getById(id);
        
        return ResponseEntity.ok().body(response);
    }

}
