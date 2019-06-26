package com.azxca1731.timeliner.service;

import com.azxca1731.timeliner.domain.Post;
import com.azxca1731.timeliner.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    public Post findPostByIdx(Long idx) {
        return postRepository.findById(idx).orElse(new Post());
    }

    public void saveNewPost(Post post) {
        post.setPublishedTime(LocalDateTime.now());
        postRepository.save(post);
        return;
    }
}
