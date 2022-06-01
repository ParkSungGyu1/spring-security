package com.spring.loginprac.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentRequestDto {
    private Long id;
    private String username;
    private String comment;
    private Date date;

}
