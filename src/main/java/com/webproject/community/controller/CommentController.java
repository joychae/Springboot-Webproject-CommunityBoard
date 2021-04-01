package com.webproject.community.controller;

import com.webproject.community.model.dto.CommentRequestDto;
import com.webproject.community.model.entity.Comment;
import com.webproject.community.security.UserDetailsImpl;
import com.webproject.community.service.CommentService;
import com.webproject.community.service.MemoService;
import com.webproject.community.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글을 작성하는 기능입니다.
    @ResponseBody
    @PostMapping("/post/read/{memoId}/comment")
    public Comment createComment(@PathVariable Long memoId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 ID
        requestDto.setMemoId(memoId);
        requestDto.setUserId(userDetails.getUser().getId());
        requestDto.setCreated_by(userDetails.getUser().getUsername());
        Comment comment = commentService.createComment(requestDto);
        return comment;
    }

    // 댓글을 삭제하는 기능입니다.
    @ResponseBody
    @DeleteMapping("/api/comments/{id}")
    public Long deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }

    // 댓글을 수정하는 기능입니다.
    @ResponseBody
    @PutMapping("/api/comments/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        commentService.commentUpdate(id, commentRequestDto);
        return id;
    }



}
