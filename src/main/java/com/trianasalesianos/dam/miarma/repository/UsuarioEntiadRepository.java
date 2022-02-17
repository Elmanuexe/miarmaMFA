package com.trianasalesianos.dam.miarma.repository;

import com.trianasalesianos.dam.miarma.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioEntiadRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);

}
