package com.azxca1731.timelineback;

import com.azxca1731.timelineback.domain.post.Post;
import com.azxca1731.timelineback.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class TimelineBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimelineBackApplication.class, args);
    }

    @Component
    public class InputRunner implements ApplicationRunner {
        @Autowired
        PostRepository repository;

        @Override
        public void run(ApplicationArguments args) throws Exception {
            IntStream.rangeClosed(1,10).forEach(
                    integer -> repository.save(
                            Post.builder()
                                    .content("컨텐트"+integer)
                                    .title("제목"+integer)
                                    .publishedTime(LocalDateTime.now())
                                    .build()
                    )
            );
        }
    }
}
