package com.demaq.backend.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.demaq.backend.dto.UsuarioDto;
import com.demaq.backend.exception.ErrorDto;
import com.demaq.backend.exception.NotFoundException;
import com.demaq.backend.exception.RestException;
import com.demaq.backend.model.Rol;
import com.demaq.backend.model.Usuario;
import com.demaq.backend.repository.IRolRepository;
import com.demaq.backend.repository.IUsuarioRepository;
import com.demaq.backend.service.iface.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements IUsuarioService {
    
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IRolRepository rolRepository;



    /* ******************************************************************************************************* */
    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> getAll() throws RestException{

        List<Usuario> usuarios = usuarioRepository.findAll();

        if(usuarios == null)
        {
            throw new NotFoundException(ErrorDto.getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(), 
                                                            "NO se encontro infomacion", 
                                                            HttpStatus.NOT_FOUND.value())
                                        );
        }

        List<UsuarioDto> usuariosDto = new ArrayList<>();

        for(Usuario usuario: usuarios)
        {
            UsuarioDto usuarioDto = new UsuarioDto();

            usuarioDto.setId(usuario.getId());
            usuarioDto.setUsername(usuario.getUsername());
            usuarioDto.setNombre(usuario.getNombre());
            usuarioDto.setApellido(usuario.getApellido());
            usuarioDto.setPassword(usuario.getPassword());
            usuarioDto.setTelefono(usuario.getTelefono());
            usuarioDto.setRol(usuario.getRol().getId());

            usuariosDto.add(usuarioDto);
        }
        return null;
    }


    /* ****************************************************************************************************************** */

    @Override
    @Transactional(readOnly = true)
    public UsuarioDto getById(Long id) throws RestException{

        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setId(usuario.getId());
        usuarioDto.setUsername(usuario.getUsername());
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setApellido(usuario.getApellido());
        usuarioDto.setPassword(usuario.getPassword());
        usuarioDto.setTelefono(usuario.getTelefono());
        usuarioDto.setRol(usuario.getRol().getId());

        return usuarioDto;
    }


    /* ****************************************************************************************************************** */

    @Override
    @Transactional
    public UsuarioDto save(UsuarioDto usuarioDto) throws RestException{

        Usuario usuario = new Usuario();

        usuario.setUsername(usuarioDto.getUsername());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
        usuario.setTelefono(usuarioDto.getTelefono());
        
        Optional<Rol> rol = rolRepository.findById(usuarioDto.getRol());
        if(!rol.isPresent())
        {
            return null;
        }
        usuario.setRol(rol.get());

        Usuario usuarioGuaradato = usuarioRepository.save(usuario);

        usuario.setId(usuarioGuaradato.getId());

        return usuarioDto;
    }


    /* ****************************************************************************************************************** */
    
    @Override
    public void deleteById(Long id) throws RestException {
        usuarioRepository.deleteById(id);
        
    }
    
} 
