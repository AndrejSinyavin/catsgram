package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping("/post/{postId}")
    public Optional<Post> findById(@PathVariable int postId) {
        return postService.findAll().stream()
                .filter(x -> x.getId() == postId)
                .findFirst();
    }

    @PostMapping("/post")
    public void create(@RequestBody Post post) {
        postService.create(post);
    }

}