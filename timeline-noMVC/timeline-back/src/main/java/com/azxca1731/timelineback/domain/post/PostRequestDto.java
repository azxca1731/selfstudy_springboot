package com.azxca1731.timelineback.domain.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {
    private String title;
    private String content;

    @Builder
    public PostRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post toPosts() {
        return Post.builder()
                    .content(content)
                    .title(title)
                    .publishedTime(LocalDateTime.now())
                    .build();
    }
}
