package com.trianasalesianos.dam.miarma.repository;

import com.trianasalesianos.dam.miarma.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);

    boolean existsById(@NotBlank UUID id);

    boolean existsByNick(@NotBlank String nick);

    boolean existsByEmail(@NotBlank String email);

    Optional<Usuario> findById(UUID id);

    Optional<Usuario> findUsuarioByNick(@NotBlank String nick);

    default Usuario getUsuario(String nick){return getUsuarioByNick(nick);}

    default Usuario getUsuarioByNick(String nick){
        return findUsuarioByNick(nick)
                .orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado el usuario con el nick"+ nick));
    }



}
