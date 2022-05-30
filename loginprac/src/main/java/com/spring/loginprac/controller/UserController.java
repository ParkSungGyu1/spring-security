package com.spring.loginprac.controller;

import com.spring.loginprac.dto.SignupRequestDto;
import com.spring.loginprac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {

        return "signup";
    }

    @PostMapping("/user/signup")
    @ResponseBody
    public String signupRequest(@RequestBody SignupRequestDto signupRequestDto){
        String result = userService.signup(signupRequestDto);
        return result;
    }
}