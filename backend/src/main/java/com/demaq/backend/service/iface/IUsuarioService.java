package com.demaq.backend.service.iface;

import java.util.List;

import com.demaq.backend.dto.UsuarioDto;
import com.demaq.backend.exception.RestException;

public interface IUsuarioService {
    
    public List<UsuarioDto> getAll() throws RestException;

    public UsuarioDto getById(Long id)  throws RestException;

    public UsuarioDto save(UsuarioDto usuarioDto) throws RestException;

    public void deleteById(Long id) throws RestException;
   
}
