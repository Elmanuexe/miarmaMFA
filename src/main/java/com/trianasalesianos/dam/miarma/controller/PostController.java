package com.trianasalesianos.dam.miarma.controller;

import com.trianasalesianos.dam.miarma.model.Post;
import com.trianasalesianos.dam.miarma.model.dto.postDto.CreatePostDto;
import com.trianasalesianos.dam.miarma.model.dto.postDto.GetPostDto;
import com.trianasalesianos.dam.miarma.model.dto.postDto.PostDtoConverter;
import com.trianasalesianos.dam.miarma.service.FileService;
import com.trianasalesianos.dam.miarma.service.impl.FileServiceImpl;
import com.trianasalesianos.dam.miarma.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;
    private final PostDtoConverter dtoConverter;

    @PostMapping("/post")
    public ResponseEntity<GetPostDto> nuevoPost(@RequestPart ("post") CreatePostDto p, @Valid @RequestPart ("file")MultipartFile file, @RequestPart("nick") String nick) throws IOException {
        if (file.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Post post = postService.nuevoPost(p, file, nick);
        return ResponseEntity.ok(dtoConverter.convertPostToPostDto(post));
    }

    @GetMapping("/post/public")
    public ResponseEntity<List<GetPostDto>> obtenerTodosPublic(){
        return ResponseEntity.ok(postService.findAllDto());
    }
}
