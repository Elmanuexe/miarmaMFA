package com.trianasalesianos.dam.miarma.controller;

import com.trianasalesianos.dam.miarma.model.Usuario;
import com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto.CreateUsuarioDto;
import com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto.GetUsuarioDto;
import com.trianasalesianos.dam.miarma.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    @PutMapping("/profile/me")
    public ResponseEntity<Usuario> editUsuario(@AuthenticationPrincipal Usuario u, @RequestBody CreateUsuarioDto dto){
        return ResponseEntity.accepted().body(usuarioService.updateUsuario(dto, u.getId()));
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<GetUsuarioDto> getprofile(@PathVariable UUID id){
        return ResponseEntity.ok().body(usuarioService.getInfoById(id));
    }

}
