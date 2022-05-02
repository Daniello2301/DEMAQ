package com.demaq.backend.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.demaq.backend.model.Producto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PedidoDto {
    
    private Long id;
    private int cantidad;
    private Date fecha;

    @JsonProperty("usuario_id")
    private Long idUsuario;

    @JsonProperty("productos")
    private List<Producto> productos = new ArrayList<>();

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    

}
