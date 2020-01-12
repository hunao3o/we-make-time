package com.gaenodap3.wmtapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Entity : JpaRepository 가 사용하는 Entity 라고 명시, 테이블과 링크될 클래스임을 나타냄.
 * @NoArgsConstructor - access : 기본 생성자의 접근 권한을 protected 로 제한
 *  - protected Posts() {} 와 같은 효과
 *  - Entity 클래스를 프로젝트 코드상에서 기본생성자로 생성되는 건 막되, JPA에서 Entity 클래스를 생성하는 것은 허용하기 위함.
 */
@Entity
@Data
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Posts {

    @Id // 해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 룰 (default: AUTO(=AI))
    private long id;

    @Column(length = 500, nullable = false) // 컬럼 (default: VARCHAR(255))
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author; // 굳이 선언하지 않아도 컬럼으로 설정

    // test에서 lombok (@AllArgsConstructor) 이 먹질 않아서 추가
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // test에서 lombok (@Getter) 이 먹질 않아서 추가
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

}