package com.demaq.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioDto {
    
    private Long id;
    private String username;
    private String nombre;
    private String apellido;
    private String password;
    private String telefono;

    @JsonProperty("rol_id")
    private Long rol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getRol() {
        return rol;
    }

    public void setRol(Long rol) {
        this.rol = rol;
    }

    
}
