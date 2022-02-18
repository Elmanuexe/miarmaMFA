package com.trianasalesianos.dam.miarma.controller;

import com.trianasalesianos.dam.miarma.model.Post;
import com.trianasalesianos.dam.miarma.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;

    @PostMapping("/post")
    public ResponseEntity<Post> nuevoPost(@RequestPart ("post") Post p, @RequestPart ("file")MultipartFile file, @RequestPart("usuarioId") UUID id) throws IOException {
        if (file.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Post post = postService.nuevoPost(p, file, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
}
