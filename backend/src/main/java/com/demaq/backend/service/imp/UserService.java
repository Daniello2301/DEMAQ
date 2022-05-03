/* package com.demaq.backend.service.imp;

import java.util.ArrayList;
import java.util.List;

import com.demaq.backend.model.Usuario;
import com.demaq.backend.service.iface.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService, IUserService{

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private IUserService userRespository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOG.info("Loading User");

        Usuario usuario = userRespository.findByUsername(username);

        if(usuario == null){
            LOG.error("No se puedo encontrar el usuario o no existe");
            throw new UsernameNotFoundException("No se encontro el Usuario" +  username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));

        UserDetails userDetails = new User(usuario.getUsername(),
                                            usuario.getPassword(),
                                            usuario.getEnabled(),
                                            true,
                                            true,
                                            true,
                                            authorities);

        return null;
    }


    @Override
    public Usuario findByUsername(String username) {        
        return userRespository.findByUsername(username);
    }



    
} */
