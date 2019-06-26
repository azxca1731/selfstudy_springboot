package com.azxca1731.timeliner.repository;

import com.azxca1731.timeliner.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title);
}
