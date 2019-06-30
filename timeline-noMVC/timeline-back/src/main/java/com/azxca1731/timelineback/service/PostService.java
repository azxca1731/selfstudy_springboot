package com.azxca1731.timelineback.service;

import com.azxca1731.timelineback.domain.post.Post;
import com.azxca1731.timelineback.domain.post.PostRequestDto;
import com.azxca1731.timelineback.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getDetailPost(Long idx) {
        return postRepository.findById(idx).orElse(null);
    }

    public Long savePost(PostRequestDto dto) {
        return postRepository.save(dto.toPosts()).getIdx();
    }
}
