package com.demaq.backend.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.demaq.backend.dto.ProductoDto;
import com.demaq.backend.exception.ErrorDto;
import com.demaq.backend.exception.NotFoundException;
import com.demaq.backend.exception.RestException;
import com.demaq.backend.model.Pedido;
import com.demaq.backend.model.Producto;
import com.demaq.backend.repository.IPedidoRepository;
import com.demaq.backend.repository.IProductoRepository;
import com.demaq.backend.service.iface.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IPedidoRepository pedidoRepository;
    
    /* *************************************************************************************************************** */
    @Override
    @Transactional(readOnly = true)
    public List<ProductoDto> getAll() throws RestException {
        
        List<Producto> productos = productoRepository.findAll();

        if(productos == null)
        {
            throw new NotFoundException(ErrorDto.getErrorDto
                                                (HttpStatus.NOT_FOUND.getReasonPhrase(), 
                                                "NO se encontro informcaion relacionada", 
                                                HttpStatus.NOT_FOUND.value()
                                                )
                                        );
        }

        List<ProductoDto> productosDto = new ArrayList<>();

        for(Producto producto: productos)
        {
            ProductoDto productoDto = new ProductoDto();

            productoDto.setId(producto.getId());
            productoDto.setDescription(producto.getDescripcion());
            productoDto.setCantidad(producto.getCantidad());
            productoDto.setFecha_ingreso(producto.getFecha());
            productoDto.setPedido(producto.getPedido().getId());

            productosDto.add(productoDto);
        }

        return productosDto;
    }

    
    /* *************************************************************************************************************** */
    @Override
    @Transactional(readOnly = true)
    public ProductoDto getById(Long id) throws RestException {

        Producto producto = productoRepository.findById(id).orElse(null);

        ProductoDto productoDto = new ProductoDto();
        productoDto.setId(producto.getId());
        productoDto.setDescription(producto.getDescripcion());
        productoDto.setCantidad(producto.getCantidad());
        productoDto.setFecha_ingreso(producto.getFecha());
        productoDto.setPedido(producto.getPedido().getId());

        return productoDto;
    }


    /* ************************************************************************************************************************** */
    @Override
    @Transactional
    public ProductoDto save(ProductoDto productoDto) throws RestException {
        
        Producto producto = new Producto();
        producto.setDescripcion(productoDto.getDescription());
        producto.setCantidad(productoDto.getCantidad());
        producto.setFecha(productoDto.getFecha_ingreso());
        Optional<Pedido> pedido = pedidoRepository
                                    .findById(producto
                                                .getPedido()
                                                .getId()
                                            );
            if(!pedido.isPresent())
            {
                return null;
            }
        producto.setPedido(pedido.get());

        Producto productoGuardado = productoRepository.save(producto);

        productoDto.setId(productoGuardado.getId());


        return productoDto;
    }

    @Override
    public void deleteById(Long id) throws RestException {
        
        productoRepository.deleteById(id);
        
    }
    
}
