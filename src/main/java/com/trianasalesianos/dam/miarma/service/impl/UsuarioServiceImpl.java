package com.trianasalesianos.dam.miarma.service.impl;

import com.trianasalesianos.dam.miarma.model.Usuario;
import com.trianasalesianos.dam.miarma.repository.UsuarioRepository;
import com.trianasalesianos.dam.miarma.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        return usuarioRepository.getById(id);

    }

    @Override
    public Usuario updateUsuario(Usuario nuevo, UUID id) {
        if (usuarioRepository.existsById(id)) {
            Usuario antiguo = usuarioRepository.findById(id).get();
            antiguo.setAvatar(nuevo.getAvatar());
            antiguo.setDireccion(nuevo.getDireccion());
            antiguo.setEmail(nuevo.getEmail());
            antiguo.setNacimiento(nuevo.getNacimiento());
            antiguo.setNick(nuevo.getNick());
            antiguo.setPassword(nuevo.getPassword());
            antiguo.setPrivado(nuevo.getPrivado());
            return usuarioRepository.save(nuevo);
        }
        else throw new EntityNotFoundException("No se ha encontrado usuario con esa ID");
    }

    @Override
    public Usuario getUsuarioByNick(String nick) {
        return null;
    }

    @Override
    public Usuario addUsuario(Usuario nuevo) {
        return null;
    }
}
