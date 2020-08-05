package com.myblog.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.entity.Post;
import com.myblog.exceptions.ResourceNotFoundException;
import com.myblog.service.PostService;
import com.myblog.util.ControllerResponse;

@RestController
@RequestMapping("api/v1")
public class PostController {


   

    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() throws ResourceNotFoundException {
        List<Post> posts=postService.getAllPosts();
        return ControllerResponse.getOkResposeEntity(posts);

    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "postId") UUID postId) throws ResourceNotFoundException {
        Optional<Post> post = postService.getPostById(postId);
        return post.map(ControllerResponse::getOkResposeEntity).
                orElseGet(ControllerResponse::getServerErrorResponseEntity);

    }

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Optional<Post> newPost = postService.save(post);
        return newPost.map(ControllerResponse::getCreatedResposeEntity).
                orElseGet(ControllerResponse::getServerErrorResponseEntity);


    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable(value ="postId") UUID postId, @RequestBody Post post) throws ResourceNotFoundException {
        Optional<Post> updatedPost = postService.updatePostbyId(post, postId);
        return updatedPost.map(ControllerResponse::getCreatedResposeEntity).orElseGet(ControllerResponse::getServerErrorResponseEntity);

    }
    
    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable(value ="postId") UUID postId)  {
        postService.deletePost(postId);

    }
}
