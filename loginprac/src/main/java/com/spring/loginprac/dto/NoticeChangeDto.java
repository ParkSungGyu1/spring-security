package com.spring.loginprac.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NoticeChangeDto {
    private Long id;
    private String username;
    private String title;
    private String description;
}
