package com.demaq.backend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Cantidad necesaria")
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @NotEmpty(message = "Id de usuario necesario")
    private Usuario usuario; 


    @NotEmpty(message = "Id de productos necesarios")
    @OneToMany(mappedBy = "pedido")
    @JsonProperty("producto")
    private List<Producto> productos = new ArrayList<>(); 

    private Date fecha_orden;

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


    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public List<Producto> getProductos() {
        return productos;
    }


    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }


    public Date getFecha_orden() {
        return fecha_orden;
    }


    public void setFecha_orden(Date fecha_orden) {
        this.fecha_orden = fecha_orden;
    }


}
