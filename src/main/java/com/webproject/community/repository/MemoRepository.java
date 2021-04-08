package com.webproject.community.repository;

import com.webproject.community.model.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    // 게시글 모두 불러온 후, 수정일을 기준으로 최신순 정렬하는 명령문 입니다.
    Page<Memo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    // 사용자 아이디로 게시글 목록을 찾은 후, 수정일을 기준으로 최신순 정렬하는 명령문 입니다.
    List<Memo> findByUserIdOrderByModifiedAtDesc(Long id);

    // 게시글을 검색하는 명령문 입니다.
    List<Memo> findByTitleContaining(String keyword);

}
