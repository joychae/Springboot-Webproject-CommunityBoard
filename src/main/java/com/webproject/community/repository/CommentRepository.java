package com.webproject.community.repository;

import com.webproject.community.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    // 매개변수로 들어가 있는 memoId 값을 가진 댓글을 모두 찾아 수정일을 기준으로 최신순 정렬해주는 명령문 입니다.
    List<Comment> findByMemoIdOrderByModifiedAtDesc(Long memoId);
    void deleteAllByMemoId(Long memoId);
}
