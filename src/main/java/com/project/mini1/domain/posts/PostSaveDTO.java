package com.project.mini1.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
// Controller에서 @RequestBody로 외부에서 데이터를 받는 경우엔 기본생성자 + set메소드를 통해서만 값이 할당
@NoArgsConstructor
public class PostSaveDTO {
    // View Layer와 DB Layer를 철저하게 역할 분리 필요

    private String title;
    private String content;
    private String author;

    @Builder
    // Builder 사용 이유: 채워야할 필드가 무엇인지 명확히 지정할 수 있음
    public PostSaveDTO(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
