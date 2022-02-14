package com.trianasalesianos.dam.miarma.security.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class JwtUsuarioResponse {

    private String email, nick, avatar, token;
}
