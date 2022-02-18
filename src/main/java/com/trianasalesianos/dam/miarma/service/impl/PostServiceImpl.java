package com.trianasalesianos.dam.miarma.service.impl;

import com.trianasalesianos.dam.miarma.model.Post;
import com.trianasalesianos.dam.miarma.model.Usuario;
import com.trianasalesianos.dam.miarma.repository.PostRepository;
import com.trianasalesianos.dam.miarma.repository.UsuarioRepository;
import com.trianasalesianos.dam.miarma.service.FileService;
import com.trianasalesianos.dam.miarma.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Log
@Service
@RequiredArgsConstructor
public class PostServiceImpl extends BaseService<Post, UUID, PostRepository> {

    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;
    private final FileServiceImpl fileService;

    public Post nuevoPost(Post post, MultipartFile file, UUID id) throws IOException {
        Usuario u = usuarioRepository.getById(id);

        String nombreArchivo=fileService.reescalarAndGuardar(file,124);
        String uri = fileService.getUri(nombreArchivo);

        Post p = Post.builder()
                .descripcion(post.getDescripcion())
                .privado(post.isPrivado())
                .titulo(post.getTitulo())
                .content(uri)
                .build();
        p.addToUsuario(u);
        return save(p);
    }
}
