package com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateUsuarioDto {

    private String nick, telefono, direccion, avatar, email, password;

    private boolean privado;

    private LocalDateTime nacimiento;
}
