package com.trianasalesianos.dam.miarma.model.dto.postDto;

import com.trianasalesianos.dam.miarma.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostDtoConverter {

    public GetPostDto convertPostToPostDto(Post p){
        return GetPostDto.builder()
                .id(p.getId())
                .content(p.getContent())
                .thumbnail(p.getThumbnail())
                .descripcion(p.getDescripcion())
                .titulo(p.getTitulo())
                .usuarioId(p.getUsuario().getId())
                .usuarioAvatar(p.getUsuario().getAvatar())
                .usuarioNick(p.getUsuario().getNick())
                .build();
    }
}
