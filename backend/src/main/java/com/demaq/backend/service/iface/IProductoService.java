package com.demaq.backend.service.iface;

import java.util.List;

import com.demaq.backend.dto.ProductoDto;
import com.demaq.backend.exception.RestException;

public interface IProductoService {

    public List<ProductoDto> getAll() throws RestException;

    public ProductoDto getById(Long id) throws RestException;

    public ProductoDto save(ProductoDto productoDTO) throws RestException;

    public void deleteById(Long id) throws RestException;
    
}
