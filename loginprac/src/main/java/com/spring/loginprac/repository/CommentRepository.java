package com.spring.loginprac.repository;

import com.spring.loginprac.model.Comment;
import com.spring.loginprac.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByNotice(Notice notice);
}
