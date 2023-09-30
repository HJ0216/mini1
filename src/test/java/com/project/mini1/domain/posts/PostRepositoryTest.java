package com.project.mini1.domain.posts;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
// 스프링 컨테이너를 띄우고 스프링과 관련되어 통합테스트를 진행할 때 사용(특히 DB와 연동되어 처리 할 경우 사용)
// @RunWith(SpringRunner.class): Junit5에서는 생략이 가능
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    @Transactional
    // 해당 범위 내 메서드가 트랜잭션이 되도록 보장, 선언적 트랜잭션
    // 연산의 원자성이 보장되어, 연산이 도중에 실패할 경우 변경사항이 Commit되지 않음
    // 클래스, 메소드에 @Transactional이 선언되면 해당 클래스에 트랜잭션이 적용된 프록시 객체 생성
    // 프록시 객체는 @Transactional이 포함된 메서드가 호출될 경우, 트랜잭션을 시작하고 Commit or Rollback을 수행
    // CheckedException or 예외가 없을 때는 Commit
    // UncheckedException이 발생하면 Rollback
    @Rollback(false) // test에서는 DB에 데이터가 저장되서는 안되므로 자동 RollBack
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

}
