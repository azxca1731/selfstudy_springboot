package com.azxca1731.webservice.web;

import com.azxca1731.webservice.domain.posts.PostsRepository;
import com.azxca1731.webservice.domain.posts.PostsSaveRequestDto;
import com.azxca1731.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class WebRestController {

    private PostsRepository postsRepository;
    private PostsService postsService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
        return postsService.save(dto);
    }
}
