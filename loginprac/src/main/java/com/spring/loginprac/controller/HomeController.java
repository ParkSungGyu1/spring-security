package com.spring.loginprac.controller;

import com.spring.loginprac.model.Notice;
import com.spring.loginprac.model.UserRoleEnum;
import com.spring.loginprac.security.UserDetailsImpl;
import com.spring.loginprac.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final NoticeService noticeService;

    @Autowired
    public HomeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model){
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());

            if (userDetails.getUser().getRole() == UserRoleEnum.ADMIN) {
                model.addAttribute("admin_role", true);
            }
        }
        return "main";
    }

    @GetMapping("/notice/detail/{id}")
    public String detailView(@PathVariable Long id, Model model){
        Notice notice = noticeService.detailView(id);
        model.addAttribute("list", notice);


        return "detail";
    }

}
