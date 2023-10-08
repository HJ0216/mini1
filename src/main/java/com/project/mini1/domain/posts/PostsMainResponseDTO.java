package com.project.mini1.domain.posts;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class PostsMainResponseDTO {

    private Long id;
    private String title;
    private String author;
    private String modifiedDate;

    public PostsMainResponseDTO(Posts entity) {
        id = entity.getId();
        title = entity.getTitle();
        author = entity.getAuthor();
        modifiedDate = toStringDateTime(entity.getModifiedDate());
    }

    // View영역에선 LocalDateTime 타입을 모르기 때문에 인식할수 있도록 toStringDateTime을 통해 문자열로 날짜형식을 변경
    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }

}
