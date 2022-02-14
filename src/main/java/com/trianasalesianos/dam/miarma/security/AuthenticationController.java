package com.trianasalesianos.dam.miarma.security;

import com.trianasalesianos.dam.miarma.model.Usuario;
import com.trianasalesianos.dam.miarma.security.dto.JwtUsuarioResponse;
import com.trianasalesianos.dam.miarma.security.dto.LoginDto;
import com.trianasalesianos.dam.miarma.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthenticationController {

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

    @GetMapping("/me")
    public ResponseEntity<?> quienSoy(@AuthenticationPrincipal Usuario usuario){
        return ResponseEntity.ok(convertUsuarioToJwtUsuarioResponse(usuario, null));
    }

    private JwtUsuarioResponse convertUsuarioToJwtUsuarioResponse(Usuario usuario, String jwt){
        return JwtUsuarioResponse.builder()
                .nick(usuario.getUsername())
                .email(usuario.getEmail())
                //TODO implementar reescalado de fichero imagen
                .avatar(usuario.getAvatar())
                .token(jwt)
                .build();
    }
}