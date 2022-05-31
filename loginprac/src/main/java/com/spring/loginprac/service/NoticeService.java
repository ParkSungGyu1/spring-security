package com.spring.loginprac.service;

import com.spring.loginprac.dto.*;
import com.spring.loginprac.model.Comment;
import com.spring.loginprac.model.Notice;
import com.spring.loginprac.repository.CommentRepository;
import com.spring.loginprac.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public  NoticeService(NoticeRepository noticeRepository, CommentRepository commentRepository){
        this.noticeRepository = noticeRepository;
        this.commentRepository = commentRepository;
    }
    public List<Notice> noticeView() {
        return noticeRepository.findAll();
    }

    public Notice detailView(Long id) {
        Notice noticeDto = noticeRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

        return noticeDto;
    }

    public void noticeWrite(NoticeDto noticeDto) {
        Notice notice = new Notice(noticeDto);
        noticeRepository.save(notice);
    }

    public PasswordCheckDto passwordCheck(PasswordCheckDto passwordCheckDto) {
        Notice noticeDto = noticeRepository.findById(passwordCheckDto.getId())
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));


        return passwordCheckDto;
    }



    public void noticeChange(NoticeChangeDto noticeChangeDto) {
        Notice notice = noticeRepository.findById(noticeChangeDto.getId())
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
        notice.setDescription(noticeChangeDto.getDescription());
        notice.setTitle(noticeChangeDto.getTitle());
        notice.setUsername(noticeChangeDto.getUsername());
        noticeRepository.save(notice);
    }

    public void noticeDelete(NoticeDeleteDto noticeDeleteDto) {
        noticeRepository.delete(noticeRepository.findById(noticeDeleteDto.getId()).orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다.")));
    }



    public List<Comment> commentView(Long id) {
        Notice notice = noticeRepository.findById(id).get();
        return commentRepository.findCommentsByNotice(notice);
    }
}
