package com.trianasalesianos.dam.miarma.controller;

import com.trianasalesianos.dam.miarma.model.Usuario;
import com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto.CreateUsuarioDto;
import com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto.GetUsuarioDto;
import com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto.UsuarioDtoConverter;
import com.trianasalesianos.dam.miarma.security.dto.JwtUsuarioResponse;
import com.trianasalesianos.dam.miarma.security.dto.LoginDto;
import com.trianasalesianos.dam.miarma.security.jwt.JwtProvider;
import com.trianasalesianos.dam.miarma.service.impl.UsuarioEntiadService;
import com.trianasalesianos.dam.miarma.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioServiceImpl usuarioService;
    private final UsuarioEntiadService usuarioEntiadService;
    private final UsuarioDtoConverter dtoConverter;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getEmail(),
                                loginDto.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Devolver una respuesta aecuada que incluya el token de usuario

        String jwt = jwtProvider.generateToken(authentication);

        Usuario usuario = (Usuario) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertUsuarioToJwtUsuarioResponse(usuario, jwt));
    }

    @GetMapping("/yo")
    public ResponseEntity<?> quienSoy(@AuthenticationPrincipal Usuario usuario){
        return ResponseEntity.ok(convertUsuarioToJwtUsuarioResponse(usuario, null));
    }

    private JwtUsuarioResponse convertUsuarioToJwtUsuarioResponse(Usuario usuario, String jwt) {
        return JwtUsuarioResponse.builder()
                .nick(usuario.getUsername())
                .email(usuario.getEmail())
                .avatar(usuario.getAvatar())
                .token(jwt)
                .build();
    }


    @PostMapping("/auth/register")
    public ResponseEntity<GetUsuarioDto> nuevoUsuario(@Valid @RequestPart("usuario") CreateUsuarioDto nuevoUs, @Valid @RequestPart("file") MultipartFile file) throws IOException {
        if (file.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Usuario guardado = usuarioEntiadService.save(nuevoUs, file);
        return ResponseEntity.ok(dtoConverter.convertUsuarioToGetUsuarioDto(guardado));
    }
}
