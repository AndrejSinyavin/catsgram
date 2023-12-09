package ru.yandex.practicum.catsgram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final List<Post> posts = new ArrayList<>();
    @Autowired
    private final UserService userService;

    public List<Post> findAll() {
        return posts;
    }

    public Post create(Post post) {
        userService.findUserByEmail(post.getAuthor());
        posts.add(post);
        return post;
    }
}
