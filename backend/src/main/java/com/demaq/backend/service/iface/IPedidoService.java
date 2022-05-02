package com.demaq.backend.service.iface;

import java.util.List;

import com.demaq.backend.dto.PedidoDto;
import com.demaq.backend.exception.RestException;

public interface IPedidoService {
    
    public List<PedidoDto> getAll() throws RestException;

    public PedidoDto getById(Long id) throws RestException;

    public PedidoDto save(PedidoDto pedidoDto) throws RestException;

    public void deleteById(long id) throws RestException;
}
