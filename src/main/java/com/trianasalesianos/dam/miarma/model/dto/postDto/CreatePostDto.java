package com.trianasalesianos.dam.miarma.model.dto.postDto;

import com.trianasalesianos.dam.miarma.model.Usuario;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreatePostDto {

    private String titulo, descripcion;

    private boolean privado;
}
