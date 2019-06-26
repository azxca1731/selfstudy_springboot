package com.azxca1731.timeliner.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Post implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime publishedTime;

    @Builder
    public Post(long idx, String title, String content, LocalDateTime publishedTime) {
        this.idx = idx;
        this.title = title;
        this.content = content;
        this.publishedTime = publishedTime;
    }
}
