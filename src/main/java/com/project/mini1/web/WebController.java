package com.project.mini1.web;

import com.project.mini1.domain.posts.PostSaveDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WebController {

    @GetMapping("/")
    public String main() {
        return "main";
    }

}
