package com.spring.loginprac.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.loginprac.dto.NoticeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Notice {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<Comment> comments = new ArrayList<>();

    public Notice(NoticeDto noticeDto) {
        this.username = noticeDto.getUsername();
        this.title = noticeDto.getTitle();
        this.description = noticeDto.getDescription();

    }
}
