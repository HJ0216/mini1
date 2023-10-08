package com.project.mini1.web;

import com.project.mini1.domain.posts.PostSaveDTO;
import com.project.mini1.domain.posts.PostsMainResponseDTO;
import com.project.mini1.webservice.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final PostService postService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

}
