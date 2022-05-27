package com.spring.loginprac.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PasswordCheckDto {
    private Long id;
    private String password;
    private boolean result;
}
