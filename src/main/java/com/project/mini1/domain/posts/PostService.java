package com.project.mini1.domain.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // Business Logic과 관련된 Component
@Transactional(readOnly = true)
// JPA에서 모든 데이터 변경이나 로직은 transaction 안에서 이뤄져야 함 →  @Transactional(spring) 활용
// 조회: readOnly=true, 성능 최적화
// 논리적 단위로 Transaction 처리를 하기 위해 Service에서 선언
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long savePost(Posts post){
        return postRepository.save(post);
    }

    public Posts findPost(Long id){
        return postRepository.findOne(id);
    }

    public List<Posts> findAllPosts(){
        return postRepository.findAll();
    }

    @Transactional
    public void deleteAllPosts(){
        postRepository.deleteAll();
    }

}
