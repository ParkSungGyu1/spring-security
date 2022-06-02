package com.spring.loginprac.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.loginprac.dto.NoticeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String date;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "post_id")
    private Notice notice;
}
