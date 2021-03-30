package com.webproject.community.repository;

import com.webproject.community.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByMemoIdOrderByModifiedAtDesc(Long memoId);
}
