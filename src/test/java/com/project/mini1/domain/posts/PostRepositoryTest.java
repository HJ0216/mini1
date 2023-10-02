package com.project.mini1.domain.posts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
// 스프링 컨테이너를 띄우고 스프링과 관련되어 통합테스트를 진행할 때 사용(특히 DB와 연동되어 처리 할 경우 사용)
// @RunWith(SpringRunner.class): Junit5에서는 생략이 가능
@Transactional
// 해당 범위 내 메서드가 트랜잭션이 되도록 보장, 선언적 트랜잭션
// 연산의 원자성이 보장되어, 연산이 도중에 실패할 경우 변경사항이 Commit되지 않음
// 클래스, 메소드에 @Transactional이 선언되면 해당 클래스에 트랜잭션이 적용된 프록시 객체 생성
// 프록시 객체는 @Transactional이 포함된 메서드가 호출될 경우, 트랜잭션을 시작하고 Commit or Rollback을 수행
// CheckedException or 예외가 없을 때는 Commit
// UncheckedException이 발생하면 Rollback
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    /* @Rollback으로 대체

    @AfterEach
    public void cleanup(){
        postRepository.deleteAll();
    }
    
     */

    @Test
    // @Rollback // test에서는 DB에 데이터가 저장되서는 안되므로 자동 RollBack → cleanup()을 작성하지 않아도 됨
    public void 게시글_저장() throws Exception {
        // given
        // 테스트 기반 환경을 구축하는 단계
        Posts post = Posts.builder()
                .title("테스트 제목")
                .content("테스트 본문")
                .author("테스트 저자")
                .build();


        // when
        // 테스트 하고자 하는 행위 선언
        Long saveId = postRepository.save(post);
        Posts findPost = postRepository.findOne(saveId);

        // then
        // 테스트 결과 검증
        assertThat(findPost.getId()).isEqualTo(post.getId());
        // findOne id의 return 값 = save id의 return 값과 같은가
        assertThat(findPost).isEqualTo(post);
        System.out.println("findPost == post: " + (findPost == post));

    }

    @Test
    public void 게시글_리스트_조회(){
        // givin
        Posts post = Posts.builder()
                .title("Test Title")
                .content("Test Content")
                .author("Test Author")
                .build();

        Long saveId = postRepository.save(post);


        // when
        List<Posts> postList = postRepository.findAll();

        // then
        Posts findPost = postList.get(0);
        assertThat(findPost.getTitle()).isEqualTo("Test Title");
        assertThat(findPost.getContent()).isEqualTo("Test Content");

    }

    @Test
    public void BaseTimeEntity_등록(){
        // given
        LocalDateTime now = LocalDateTime.now();
        Posts post = Posts.builder()
                .title("JPA Auditing Title")
                .content("JPA Auditing Content")
                .author("JPA Auditing Author")
                .build();
        Long saveId = postRepository.save(post);

        // when
        Posts findPost = postRepository.findOne(saveId);

        // then
        assertTrue(findPost.getCreatedDate().isAfter(now));
        assertTrue(findPost.getModifiedDate().isAfter(now));
        // isAfter: 날짜나 시간이 특정 시점 이후인지 확인
    }

}
