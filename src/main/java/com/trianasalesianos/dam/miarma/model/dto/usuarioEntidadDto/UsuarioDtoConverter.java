package com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto;

import com.trianasalesianos.dam.miarma.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoConverter {

    public GetUsuarioDto convertUsuarioToGetUsuarioDto(Usuario u) {
        return GetUsuarioDto.builder()
                .id(u.getId())
                .avatar(u.getAvatar())
                .nick(u.getNick())
                .direccion(u.getDireccion())
                .email(u.getEmail())
                .nacimiento(u.getNacimiento())
                .privado(u.getPrivado())
                .telefono(u.getTelefono())
                .build();
    }
}
