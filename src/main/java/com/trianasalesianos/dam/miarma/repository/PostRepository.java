package com.trianasalesianos.dam.miarma.repository;

import com.trianasalesianos.dam.miarma.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

}
