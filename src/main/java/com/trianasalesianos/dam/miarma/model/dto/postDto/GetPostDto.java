package com.trianasalesianos.dam.miarma.model.dto.postDto;

import lombok.*;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetPostDto {

    private UUID id;

    private String titulo, descripcion, content;

    private UUID usuarioId;

    private String usuarioNick;

    private String usuarioAvatar;
}
