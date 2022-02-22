package com.trianasalesianos.dam.miarma.service.impl;

import com.trianasalesianos.dam.miarma.errores.excepciones.EntityNotFoundException;
import com.trianasalesianos.dam.miarma.model.Post;
import com.trianasalesianos.dam.miarma.model.Usuario;
import com.trianasalesianos.dam.miarma.model.dto.postDto.CreatePostDto;
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

    public Post nuevoPost(CreatePostDto post, MultipartFile file, String nick) throws IOException {
        if (usuarioRepository.existsByNick(nick)){
            Usuario u = usuarioRepository.findUsuarioByNick(nick).get();

            String fileName=fileService.saveFile(file);
            String uri = fileService.getUri(fileName);

            String thumbnail=fileService.reescalarAndGuardar(file,124);
            String thumb_uri = fileService.getUri(thumbnail);

            Post p = Post.builder()
                    .descripcion(post.getDescripcion())
                    .privado(post.isPrivado())
                    .titulo(post.getTitulo())
                    .thumbnail(thumb_uri)
                    .content(uri)
                    .build();
            p.addToUsuario(u);
            return save(p);
        }else {
            throw  new EntityNotFoundException("No se ha encontrado al usuario con el nick" +nick);
        }
    }
}
