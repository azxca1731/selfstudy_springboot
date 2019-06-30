package com.azxca1731.timelineback.repository;

import com.azxca1731.timelineback.domain.post.Post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    private final static String title="test";
    private final static String content="타이틀";

    @Autowired
    PostRepository postRepository;

    @Before
    public void init() {
        Post newPost = Post.builder()
                .title(title)
                .content(content)
                .publishedTime(LocalDateTime.now())
                .build();
        postRepository.save(newPost);
    }

    @Test
    public void 포스트클래스_제대로_저장_되는지_확인() {
        List<Post> postList = postRepository.findAll();
        Post findedPost = postList.get(0);
        assertThat(findedPost.getContent(),is(content));
        assertThat(findedPost.getTitle(),is(title));
    }

    @Test
    public void 제대로_찾을_수_있는지_확인() {
        Post findedPost = postRepository.findByTitle(title);

        assertThat(findedPost.getContent(),is(content));
        assertThat(findedPost.getTitle(),is(title));
        assertTrue(findedPost.getPublishedTime().isBefore(LocalDateTime.now()));
    }
}
