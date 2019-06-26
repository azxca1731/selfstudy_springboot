package com.azxca1731.timeliner.runner;

import com.azxca1731.timeliner.domain.Post;
import com.azxca1731.timeliner.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@Component
public class PostInputRunner implements CommandLineRunner {

    @Autowired
    PostService postService;

    @Override
    public void run(String... args) throws Exception {
        IntStream.rangeClosed(1,10).forEach(i->{
            postService.saveNewPost(
                    Post.builder()
                        .content("테스트용"+i)
                        .title("제목"+i)
                        .publishedTime(LocalDateTime.now())
                        .build()
            );
        });
    }
}
