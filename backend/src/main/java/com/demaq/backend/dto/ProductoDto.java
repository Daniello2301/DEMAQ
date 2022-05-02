package com.demaq.backend.dto;

import java.util.Date;


public class ProductoDto {
 
    private Long id;
    private String description;
    private int cantidad;
    private Date fecha_ingreso;
    private Long pedidoId;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }
    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
    public Long getPedido() {
        return pedidoId;
    }
    public void setPedido(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    
}
