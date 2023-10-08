package com.project.mini1.webservice;

import com.project.mini1.domain.posts.PostRepository;
import com.project.mini1.domain.posts.PostSaveDTO;
import com.project.mini1.domain.posts.Posts;
import com.project.mini1.domain.posts.PostsMainResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service // Business Logic과 관련된 Component
@Transactional(readOnly = true)
// JPA에서 모든 데이터 변경이나 로직은 transaction 안에서 이뤄져야 함 →  @Transactional(spring) 활용
// 조회: readOnly=true, 성능 최적화
// 논리적 단위로 Transaction 처리를 하기 위해 Service에서 선언
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    // Commit과 Rollback(Exception 발생 시)을 통해서 데이터 무결성 유지
    public Long savePost(PostSaveDTO postSaveDTO){
        return postRepository.save(postSaveDTO.toEntity());
    }

    public Posts findPost(Long id){
        return postRepository.findOne(id);
    }

    public List<PostsMainResponseDTO> findAllPostsDesc(){
        return postRepository.findAllDesc()
                .map(PostsMainResponseDTO::new)
                // .map(posts -> new PostsMainResponseDTO(posts))
                // 이전 단계에서 반환된 게시물 데이터 스트림을 PostsMainResponseDTO 클래스의 생성자로 매핑
                .collect(Collectors.toList());
                // 스트림의 각 요소를 컬렉션(여기서는 리스트)으로 수집
                // PostsMainResponseDTO로 매핑된 게시물이 DTO 객체의 리스트로 반환
    }

    @Transactional
    public void deleteAllPosts(){
        postRepository.deleteAll();
    }

}
