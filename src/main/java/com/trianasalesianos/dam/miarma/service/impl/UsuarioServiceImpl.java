package com.trianasalesianos.dam.miarma.service.impl;

import com.trianasalesianos.dam.miarma.model.Usuario;
import com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto.CreateUsuarioDto;
import com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto.GetUsuarioDto;
import com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto.UsuarioDtoConverter;
import com.trianasalesianos.dam.miarma.repository.UsuarioRepository;
import com.trianasalesianos.dam.miarma.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> findById(UUID id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario getInfoById(UUID id) {
        if (usuarioRepository.existsById(id)) {
            return usuarioRepository.getById(id);
        } else {
            throw new EntityNotFoundException("No se ha encontrado usuario con esa ID");
        }

    }

    @Override
    public Usuario updateUsuario(CreateUsuarioDto nuevo, UUID id) {
        Usuario antiguo = findById(id).get();
        antiguo.setAvatar(nuevo.getUri());
        antiguo.setDireccion(nuevo.getDireccion());
        antiguo.setEmail(nuevo.getEmail());
        antiguo.setNacimiento(nuevo.getNacimiento());
        antiguo.setNick(nuevo.getNick());
        antiguo.setPassword(nuevo.getPassword());
        antiguo.setPrivado(nuevo.getPrivado());
        return usuarioRepository.save(antiguo);
    }

    @Override
    public Usuario getUsuarioByNick(String nick) {
        return usuarioRepository.getUsuario(nick);
    }

}
