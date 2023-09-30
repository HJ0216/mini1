package com.project.mini1.domain.posts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
// 언더스코어 네이밍(_)으로 이름을 매칭
// Ex. SalesManager.java -> sales_manager table
@Getter
/*
* Setter를 사용하지 않는 이유
*
* 클래스의 인스턴스 값들이 언제 어디서 변해야하는지 코드상으로 명확히 구분
*
* */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 파라미터가 없는 기본 생성자를 생성
// protected Posts() {}와 같은 효과
/*
* Protected 선언 이유
*
* 클래스 외부에서 직접 접근할 수 없고 하위 클래스에서만 사용할 수 있음
* Entity 클래스를 프로젝트 코드상에서 기본생성자로 생성하는 것을 방지
* JPA에서 Entity 클래스를 생성하는것은 허용
*
* */
//

public class Posts {

    @Id
    @GeneratedValue
    private Long id;
    /*
    * PK를 유니크키 또는 복합키로 설정하면 좋지 않은 이유
    *
    * 1. FK 사용 시, 테이블이 복잡해질 수 있음
    * 2. PK는 주로 테이블에서 검색 및 조인 작업을 가속화하는 데 사용
    *    복잡한 키를 PK로 사용하면 인덱스의 효율성이 감소
    * 3. 유니크한 조건이 변경될 경우 문제가 발생
    *
    * */

    @Column(length = 500, nullable = false)
    // length default = 255
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    // columnDefinition: DB 컬럼 정보를 직접 설정
    private String content;

    private String author;

    @Builder
    // Builder 사용 이유: 채워야할 필드가 무엇인지 명확히 지정할 수 있음
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
