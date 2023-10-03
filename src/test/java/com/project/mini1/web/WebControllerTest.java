package com.project.mini1.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// 테스트할 때 무작위로 할당된 포트 번호를 사용하여 내장 웹 서버를 실행하도록 지시
//  테스트 시에 포트 충돌을 방지할 수 있으며 여러 테스트를 병렬로 실행할 때 유용
// MockMvc 대신 RestTemplate를 사용
public class WebControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    // HTTP 요청을 보내고 HTTP 응답을 받는 데 사용되는 Spring Framework의 클래스

    @Test
    public void 메인_페이지_로드(){
        // given

        // when
        String body = this.restTemplate.getForObject("/", String.class);
        // Retrieve a representation by doing a GET on the specified URL.
        // The response (if any) is converted and returned.

        // then
        assertThat(body).contains("스프링부트로 시작하는 웹 서비스");

    }

}
