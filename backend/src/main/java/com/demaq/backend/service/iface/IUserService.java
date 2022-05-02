package com.demaq.backend.service.iface;

import com.demaq.backend.model.Usuario;

public interface IUserService {
    
    public Usuario findByUsername(String username);
}
