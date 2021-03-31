package com.webproject.community.repository;

import com.webproject.community.model.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    // 게시글 모두 불러온 후, 수정일을 기준으로 최신순 정렬
    List<Memo> findAllByOrderByModifiedAtDesc();

    // 사용자 아이디로 게시글 목록을 찾은 후, 수정일을 기준으로 최신순 정렬
    List<Memo> findByUserIdOrderByModifiedAtDesc(Long id);

    // 게시글 검색
    List<Memo> findByTitleContaining(String keyword);

}
