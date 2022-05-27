package com.spring.loginprac.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NoticeDto {
    private String username;
    private String password;
    private String title;
    private String description;
}
