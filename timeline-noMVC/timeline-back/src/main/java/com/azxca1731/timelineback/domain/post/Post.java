package com.azxca1731.timelineback.domain.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime publishedTime;

    @Builder
    public Post(String title, String content, LocalDateTime publishedTime) {
        this.title = title;
        this.content = content;
        this.publishedTime = publishedTime;
    }
}
