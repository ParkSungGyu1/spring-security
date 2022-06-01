package com.spring.loginprac.controller;

import com.spring.loginprac.dto.*;
import com.spring.loginprac.model.Comment;
import com.spring.loginprac.model.Notice;
import com.spring.loginprac.security.UserDetailsImpl;
import com.spring.loginprac.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService){
        this.noticeService = noticeService;
    }

    //notice 전체 불러오기
    @GetMapping("/notice")
    public List<Notice> noticeView(){
        return noticeService.noticeView();
    }

    //게시글 디테일 확인
    //PathVariable 하는법  ==> /notice/detail/1
    /*@GetMapping("/notice/detail/{id}")
    public Notice detailView(@PathVariable Long id){
        return noticeService.detailView(id);
    }*/

    //RequestParam 하는법  ==> /notice/detail?id=1
    /*@GetMapping("/notice/detail")
    public Notice detailView(@RequestParam("id") Long id){
        return noticeService.detailView(id);
    }*/


    //게시글 수정
    @PatchMapping("/notice/detail/change")
    public String noticeChange(@RequestBody NoticeChangeDto noticeChangeDto){
        noticeService.noticeChange(noticeChangeDto);
        return "1";
    }

    //게시글 삭제
    @DeleteMapping("/notice/detail/delete/{id}")
    public String noticeDelete(@PathVariable Long id){
        noticeService.noticeDelete(id);
        return "1";
    }

    //저장 기능 ==> 저장이 완료 되었을 때 Http 상태를 리턴하고 싶다.
    @PostMapping("/notice/write")
    public String noticeWrite(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody NoticeWriteDto noticeWriteDto){

        NoticeDto noticeDto = new NoticeDto();

        noticeDto.setUsername(userDetails.getUser().getUsername());


        noticeDto.setTitle(noticeWriteDto.getTitle());
        noticeDto.setDescription(noticeWriteDto.getDescription());
        noticeService.noticeWrite(noticeDto);

        return "1";
    }


    //댓글 보기
    @GetMapping("/notice/detail/comment/{id}")
    public List<Comment> commentView(@PathVariable Long id){
        return noticeService.commentView(id);
    }

    @PostMapping("/notice/detail/comment")
    public String commentWrite(@RequestBody CommentRequestDto commentRequestDto){
        noticeService.commentWrite(commentRequestDto);
        return "1";
    }

}