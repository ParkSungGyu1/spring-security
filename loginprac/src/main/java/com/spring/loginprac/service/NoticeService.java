package com.spring.loginprac.service;

import com.spring.loginprac.dto.NoticeChangeDto;
import com.spring.loginprac.dto.NoticeDeleteDto;
import com.spring.loginprac.dto.NoticeDto;
import com.spring.loginprac.dto.PasswordCheckDto;
import com.spring.loginprac.model.Notice;
import com.spring.loginprac.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public  NoticeService(NoticeRepository noticeRepository){
        this.noticeRepository = noticeRepository;
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
        if(noticeDto.getPassword().equals(passwordCheckDto.getPassword())){
            passwordCheckDto.setResult(true);
        }else{
            passwordCheckDto.setResult(false);
        }

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
}
