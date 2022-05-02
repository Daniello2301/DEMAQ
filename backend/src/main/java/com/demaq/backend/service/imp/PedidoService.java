package com.demaq.backend.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.demaq.backend.dto.PedidoDto;
import com.demaq.backend.exception.ErrorDto;
import com.demaq.backend.exception.NotFoundException;
import com.demaq.backend.exception.RestException;
import com.demaq.backend.model.Pedido;
import com.demaq.backend.model.Usuario;
import com.demaq.backend.repository.IPedidoRepository;
import com.demaq.backend.repository.IUsuarioRepository;
import com.demaq.backend.service.iface.IPedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService implements IPedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;


    /* ************************************************************************************************************** */
    @Override
    @Transactional(readOnly = true)
    public List<PedidoDto> getAll() throws RestException {
        
        List<Pedido> pedidos = pedidoRepository.findAll();

        if(pedidos == null)
        {
            throw new NotFoundException(ErrorDto
                                            .getErrorDto(
                                                    HttpStatus.NOT_FOUND.getReasonPhrase(), 
                                                    "No se encontro la informacion relacionada", 
                                                    HttpStatus.NOT_FOUND.value()
                                                        )
                                        );
        }

        List<PedidoDto> pedidosDto = new ArrayList<>();
        for(Pedido pedido: pedidos)
        {
            PedidoDto pedidoDto = new PedidoDto();

            pedidoDto.setId(pedido.getId());
            pedidoDto.setCantidad(pedido.getCantidad());
            pedidoDto.setFecha(pedido.getFecha_orden());
            pedidoDto.setIdUsuario(pedido.getUsuario().getId());
            pedidoDto.setProductos(pedido.getProductos());

            pedidosDto.add(pedidoDto);
        }
        return pedidosDto;        
    }

    @Override
    public PedidoDto getById(Long id) throws RestException {
        
        Pedido pedido = pedidoRepository.findById(id).orElse(null);

        PedidoDto pedidoDto = new PedidoDto();

        pedidoDto.setId(pedido.getId());
        pedidoDto.setCantidad(pedido.getCantidad());
        pedidoDto.setFecha(pedido.getFecha_orden());
        pedidoDto.setIdUsuario(pedido.getUsuario().getId());
        pedidoDto.setProductos(pedido.getProductos());

        return pedidoDto;
    }



    @Override
    public PedidoDto save(PedidoDto pedidoDto) throws RestException {
        
        Pedido pedido = new Pedido();

        pedido.setCantidad(pedidoDto.getCantidad());
        pedido.setFecha_orden(pedidoDto.getFecha());
        Optional<Usuario> usuario = usuarioRepository.findById(pedidoDto.getIdUsuario());
        if(!usuario.isPresent())
        {
            return null;
        }
        pedido.setUsuario(usuario.get());

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        pedidoDto.setId(pedidoGuardado.getId());

        return pedidoDto;
    }

    @Override
    public void deleteById(long id) throws RestException {
        pedidoRepository.deleteById(id);
        
    }
    
}
