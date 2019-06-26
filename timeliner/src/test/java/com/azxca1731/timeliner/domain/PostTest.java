package com.azxca1731.timeliner.domain;

import com.azxca1731.timeliner.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PostTest {
    @Autowired
    PostRepository postRepository;

    @Before
    public void init() {
        Post post = Post.builder()
                .title("안녕하세요")
                .content("안녕!")
                .publishedTime(LocalDateTime.now())
                .build();
        postRepository.save(post);
    }

    @Test
    public void 제대로_포스트가_저장되는지_확인() {
        Post post = postRepository.findByTitle("안녕하세요");

        assertThat(post.getContent(),is("안녕!"));
    }
}
