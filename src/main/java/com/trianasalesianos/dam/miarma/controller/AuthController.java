package com.trianasalesianos.dam.miarma.controller;

import com.trianasalesianos.dam.miarma.model.Usuario;
import com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto.CreateUsuarioDto;
import com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto.GetUsuarioDto;
import com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto.UsuarioDtoConverter;
import com.trianasalesianos.dam.miarma.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioServiceImpl usuarioService;
    private final UsuarioDtoConverter dtoConverter;

    /* @PostMapping("/auth/register/user")
    public ResponseEntity<GetUsuarioDto> nuevoUsuario(@RequestBody CreateUsuarioDto nUs){
        Usuario guardado = usuarioEntiadService.save(nUs);

        if (guardado == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(usuarioDtoConverter.convertUsuarioToGetUsuarioDto(guardado));
    }*/

    //TODO implementar imagen y reescalado
    @PostMapping("/auth/register")
    public ResponseEntity<GetUsuarioDto> nuevoUsuario(@Valid @RequestBody CreateUsuarioDto nuevoUs){
        Usuario guardado = usuarioService.addUsuario(nuevoUs);
        return ResponseEntity.ok(dtoConverter.convertUsuarioToGetUsuarioDto(guardado));
    }
}
