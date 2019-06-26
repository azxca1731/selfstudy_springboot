package com.azxca1731.timeliner.service;

import com.azxca1731.timeliner.domain.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    private final static String content="확인";
    private final static String title="테스트";

    @Autowired
    PostService postService;


    @Test
    public void 제대로_포스트가_생성되는지_확인(){

        //given
        Post newPost = Post.builder()
                .content(content)
                .title(title)
                .publishedTime(LocalDateTime.now())
                .build();

        postService.saveNewPost(newPost);
        Post savePost = postService.findPostByIdx(newPost.getIdx());

        assertThat(savePost.getContent(),is(content));
        assertThat(savePost.getTitle(),is(title));
    }
}
