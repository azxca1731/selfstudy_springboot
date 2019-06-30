package com.azxca1731.timelineback.controller;

import com.azxca1731.timelineback.domain.post.Post;
import com.azxca1731.timelineback.domain.post.PostRequestDto;
import com.azxca1731.timelineback.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/{idx}")
    public Post getDetailPosts(@PathVariable long idx) {
        return postService.getDetailPost(idx);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public Long savePost(@ModelAttribute PostRequestDto postRequestDto) {
        return postService.savePost(postRequestDto);
    }
}
