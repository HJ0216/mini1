package com.project.mini1.web;

import com.project.mini1.domain.posts.PostSaveDTO;
import com.project.mini1.domain.posts.PostsMainResponseDTO;
import com.project.mini1.webservice.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebRestController {

    private final PostService postService;
    // final 키워드가 붙은 Field는 객체가 생성되는 시점에 반드시 값이 초기화 되어야 함
    // 'final' 이 붙은 Field 중에 초기화 되지 않은 모든 Field를 Argument로 설정

    // Spring 권장: 생성자를 통한 의존성 주입
    // 한번 의존성을 주입받은 객체는 프로그램이 끝날때 까지 변하지 않는 특징을 가지므로 불변성(immutable)을 표시해주는 것이 좋기 때문

    // 스프링 프레임워크 4.3 버전부터 클래스에 단 하나의 생성자만 존재하면, 그 생성자에 @Autowired 어노테이션을 생략해도 스프링이 자동으로 의존성 주입을 수행


    @GetMapping("/hello")
    public String hello(){
        return "Hello, IT World!";
    }

    @PostMapping("/post")
    public Long savePost(@RequestBody PostSaveDTO postSaveDTO){
        return postService.savePost(postSaveDTO);
        // Controller에서 Dto.toEntity로 Service에 전달하지 않는 이유
        // Controller와 Service 의 역할을 분리하기 위함
        // Service: 비지니스 로직 & 트랜잭션 관리
        // Controller: View와 연동되는 부분
    }

    @PostMapping("/getAllPost")
    public List<PostsMainResponseDTO> getAllPost() {
        return postService.findAllPostsDesc();
    }

}
