package com.trianasalesianos.dam.miarma.service;

import com.trianasalesianos.dam.miarma.model.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {

    Optional<Usuario> findById(UUID id);

    Usuario getInfoById(UUID id);

    Usuario updateUsuario(Usuario nuevo, UUID id);

    Usuario getUsuarioByNick(String nick);

    Usuario addUsuario(Usuario nuevo);





}
