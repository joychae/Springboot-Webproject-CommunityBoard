package com.webproject.community.service;

import com.webproject.community.model.dto.CommentRequestDto;
import com.webproject.community.model.entity.Comment;
import com.webproject.community.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    // 댓글 등록 기능
    public Comment createComment(CommentRequestDto requestDto) {
        Comment comment = new Comment(requestDto);
        commentRepository.save(comment);
        return comment;
    }

    public List<Comment> getComments(Long memoId) {
        return commentRepository.findByMemoIdOrderByModifiedAtDesc(memoId);
    }
}
