package com.sofiaq.shiritai.security.repository;

import com.sofiaq.shiritai.security.enums.ERol;
import com.sofiaq.shiritai.security.model.Rol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByNombre(ERol nombre);
}
