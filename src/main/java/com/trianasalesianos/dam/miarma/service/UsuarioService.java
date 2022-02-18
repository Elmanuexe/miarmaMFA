package com.trianasalesianos.dam.miarma.service;

import com.trianasalesianos.dam.miarma.model.Usuario;
import com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto.CreateUsuarioDto;
import com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto.GetUsuarioDto;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {

    Optional<Usuario> findById(UUID id);

    Usuario getInfoById(UUID id);

    Usuario updateUsuario(CreateUsuarioDto nuevo, UUID id);





}
