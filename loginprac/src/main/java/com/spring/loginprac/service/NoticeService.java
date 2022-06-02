package com.spring.loginprac.service;

import com.spring.loginprac.dto.*;
import com.spring.loginprac.model.Comment;
import com.spring.loginprac.model.Notice;
import com.spring.loginprac.repository.CommentRepository;
import com.spring.loginprac.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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


    public void noticeChange(NoticeChangeDto noticeChangeDto) {
        Notice notice = noticeRepository.findById(noticeChangeDto.getId())
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
        notice.setDescription(noticeChangeDto.getDescription());
        notice.setTitle(noticeChangeDto.getTitle());
        noticeRepository.save(notice);
    }


    public void noticeDelete(Long id) {
        noticeRepository.delete(noticeRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다.")));
    }



    public List<Comment> commentView(Long id) {
        Notice notice = noticeRepository.findById(id).get();

        return commentRepository.findCommentsByNoticeOrderByDateDesc(notice);
    }

    public void commentWrite(CommentRequestDto commentRequestDto) {
        Optional<Notice> notice = noticeRepository.findById(commentRequestDto.getId());
        Comment comment = new Comment();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");
        comment.setNotice(notice.get());
        comment.setComment(commentRequestDto.getComment());
        comment.setUsername(commentRequestDto.getUsername());
        comment.setDate(format.format(date));


        commentRepository.save(comment);
    }
    public void commentChange(CommentChangeDto commentChangeDto) {
        Comment comment = commentRepository.findById(commentChangeDto.getId())
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
        comment.setComment(commentChangeDto.getComment());
        commentRepository.save(comment);
    }
    public void commentDelete(Long id) {
        commentRepository.delete(commentRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다.")));
    }

}
