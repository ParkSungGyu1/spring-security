package com.spring.loginprac.repository;

import com.spring.loginprac.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
}

