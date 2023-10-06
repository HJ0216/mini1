package com.project.mini1.webservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.project.mini1.domain.posts.PostRepository;
import com.project.mini1.domain.posts.PostSaveDTO;
import com.project.mini1.domain.posts.Posts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void DTO데이터_POST테이블_저장(){
        // given
        PostSaveDTO dto = PostSaveDTO.builder()
                .title("서비스 타이틀")
                .content("서비스 컨텐츠")
                .author("서비스 작가")
                .build();

        // when
        Long saveId = postService.savePost(dto);
        Posts findPost = postRepository.findOne(saveId);

        // then
        // Dto 클래스가 service.save 메소드에 전달되면, DB에 잘 저장되었는지 검증
        assertThat(findPost.getTitle()).isEqualTo(dto.getTitle());
        assertThat(findPost.getContent()).isEqualTo(dto.getContent());
        assertThat(findPost.getAuthor()).isEqualTo(dto.getAuthor());

    }

}
