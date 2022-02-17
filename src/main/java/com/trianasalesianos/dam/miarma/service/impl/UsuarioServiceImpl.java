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
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioDtoConverter dtoConverter;

    @Override
    public Optional<Usuario> findById(UUID id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario getInfoById(UUID id) {
        if (usuarioRepository.existsById(id)){
            return usuarioRepository.getById(id);
        }else {
            throw new EntityNotFoundException("No se ha encontrado usuario con esa ID");
        }

    }

    @Override
    public Usuario updateUsuario(CreateUsuarioDto nuevo, UUID id) {
            Usuario antiguo = findById(id).get();
            antiguo.setAvatar(nuevo.getAvatar());
            antiguo.setDireccion(nuevo.getDireccion());
            antiguo.setEmail(nuevo.getEmail());
            antiguo.setNacimiento(nuevo.getNacimiento());
            antiguo.setNick(nuevo.getNick());
            antiguo.setPassword(nuevo.getPassword());
            antiguo.setPrivado(nuevo.getPrivado());
            return usuarioRepository.save(antiguo);
    }

    @Override
    public Usuario findByEmail(String email) {
        if (usuarioRepository.existsByEmail(email)){
            return usuarioRepository.findByEmail(email).get();
        }else {
            throw new EntityNotFoundException("No se ha encontrado usuarios con este email");
        }
    }

    @Override
    public Usuario getUsuarioByNick(String nick) {
        return usuarioRepository.getUsuario(nick);
    }

    @Override
    public Usuario addUsuario(CreateUsuarioDto nuevo) {
        Usuario u = Usuario.builder()
                .password(passwordEncoder.encode(nuevo.getPassword()))
                .nick(nuevo.getNick())
                .email(nuevo.getEmail())
                .direccion(nuevo.getDireccion())
                .telefono(nuevo.getTelefono())
                .privado(nuevo.getPrivado())
                .nacimiento(nuevo.getNacimiento())
                .createdAt(LocalDateTime.now())
                .build();
        return usuarioRepository.save(u);
    }

    @Override
    public Usuario loadUserByUsername(String email) {return findByEmail(email);}
}
