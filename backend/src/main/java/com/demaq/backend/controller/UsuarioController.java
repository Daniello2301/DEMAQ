package com.demaq.backend.controller;

import java.util.List;

import com.demaq.backend.dto.UsuarioDto;
import com.demaq.backend.exception.BadRequestException;
import com.demaq.backend.exception.ErrorDto;
import com.demaq.backend.exception.InternalServerErrorException;
import com.demaq.backend.exception.NotFoundException;
import com.demaq.backend.exception.RestException;
import com.demaq.backend.service.iface.IUsuarioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IUsuarioService usuarioService;


    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> getAll() throws RestException
    {

        LOG.info("Usaurios");
        List<UsuarioDto> response = usuarioService.getAll();

        return ResponseEntity.ok().body(response);
    }




    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getById(@PathVariable Long id) throws RestException
    {
        UsuarioDto response = usuarioService.getById(id);

        return ResponseEntity.ok().body(response);
    }





    @PostMapping(consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<UsuarioDto> create(@RequestBody UsuarioDto usuarioDto) throws RestException
    {
       try 
       {
           UsuarioDto response = usuarioService.save(usuarioDto);
           
           return new ResponseEntity<>(response, HttpStatus.CREATED);

       } catch (BadRequestException e) 
       {
           LOG.error("Error", e);
           throw e;           //TODO: handle exception
       }catch (Exception e)
       {
           throw new InternalServerErrorException(
               ErrorDto.getErrorDto(
                                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
                                "Error interno de servidor", 
                                HttpStatus.INTERNAL_SERVER_ERROR.value()
                                )
           );
       }
    }



    public void deleteById(@PathVariable Long id) throws RestException
    {
        UsuarioDto usuarioDto = usuarioService.getById(id);

        if(usuarioDto == null)
        {
            throw new NotFoundException(ErrorDto.getErrorDto(
                                                    HttpStatus.NOT_FOUND.getReasonPhrase(), 
                                                    "No se econtro el usaurio", 
                                                    HttpStatus.NOT_FOUND.value()
                                                    )
                                        );
        }
        else
        {
            usuarioService.deleteById(id);
        }
    }

}