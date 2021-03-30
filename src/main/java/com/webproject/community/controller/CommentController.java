package com.webproject.community.controller;

import com.webproject.community.model.dto.CommentRequestDto;
import com.webproject.community.model.entity.Comment;
import com.webproject.community.security.UserDetailsImpl;
import com.webproject.community.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
        requestDto.setUserId(userDetails.getUser().getAccountId());
        requestDto.setCreated_by(userDetails.getUser().getUsername());
        Comment comment = commentService.createComment(requestDto);
        return comment;
    }

}
