package com.webproject.community.service;

import com.webproject.community.model.dto.CommentRequestDto;
import com.webproject.community.model.entity.Comment;
import com.webproject.community.model.entity.Memo;
import com.webproject.community.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final UserService userService;
    private final MemoService memoService;
    private final CommentRepository commentRepository;

    // 댓글 생성 기능
    public Comment createComment(CommentRequestDto requestDto) {
        Comment comment = new Comment(requestDto, memoService, userService);
        commentRepository.save(comment);
        return comment;
    }

    // 댓글 조회 기능
    public List<Comment> getComments(Long memoId) {
        return commentRepository.findByMemoIdOrderByModifiedAtDesc(memoId);
    }

    // 댓글 수정 기능
    public Long commentUpdate(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 댓글이 존재하지 않습니다")
        );
        comment.update(requestDto);
        return comment.getId();
    }

    // 댓글 삭제 기능
    public Long deleteComment(Long id) {
       commentRepository.deleteById(id);
       return id;
    }

}
