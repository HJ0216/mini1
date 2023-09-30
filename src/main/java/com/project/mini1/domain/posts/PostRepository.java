package com.project.mini1.domain.posts;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // DB에 접근하는 Component
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public Long save(Posts post){
        if(post.getId()==null){
            em.persist(post);
            // 영속화되기 전까지 item_id=null, item_id=null → 새로 생성한 객체 → 신규 등록
        } else {
            em.merge(post);
            // 이미 등록된 id가 있을 경우 → 새롭게 입력된 값으로 전부 대체
        }
        return post.getId();
        // post 객체는 고유의 ID(PK)를 갖고 DB에 저장됨 → 해당 ID를 반환하여 참조할 수 있도록 함
    }

    public Posts findOne(Long id) {
        return em.find(Posts.class, id);
    }

    public List<Posts> findAll(){
        return em.createQuery("select p from Posts p", Posts.class)
                .getResultList();
    }

}
