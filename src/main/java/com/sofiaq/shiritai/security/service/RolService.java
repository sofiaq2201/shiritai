package com.sofiaq.shiritai.security.service;

import com.sofiaq.shiritai.security.enums.ERol;
import com.sofiaq.shiritai.security.model.Rol;
import com.sofiaq.shiritai.security.repository.RolRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
    @Autowired
    RolRepository rolRepo;
    
    public Optional<Rol> getByNombre(ERol nombre){
        return rolRepo.findByNombre(nombre);
    }
}
