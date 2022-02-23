package com.trianasalesianos.dam.miarma.model.dto.usuarioEntidadDto;

import com.trianasalesianos.dam.miarma.model.dto.postDto.GetPostDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowUsuarioDto {

    private UUID id;

    private String nick, telefono, direccion, email, avatar;

    private boolean privado;

    private List<GetPostDto> publicaciones;

    private LocalDate nacimiento;
}
