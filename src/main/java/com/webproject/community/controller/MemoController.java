package com.webproject.community.controller;

import com.webproject.community.model.dto.MemoRequestDto;
import com.webproject.community.model.entity.Memo;
import com.webproject.community.security.UserDetailsImpl;
import com.webproject.community.service.CommentService;
import com.webproject.community.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    private final CommentService commentService;

    // 게시글 생성 시 클라이언트에서 게시글 제목과 내용을 받아온다.
    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        requestDto.setUserId(userDetails.getUser().getId());
        Memo memo = memoService.createMemo(requestDto);
        return memo;
    }
    // 게시글을 삭제하는 기능입니다.
    @ResponseBody
    @DeleteMapping("/api/memos/{id}")
    public Long deleteComment(@PathVariable Long id) {
        commentService.deleteCommentByMemoId(id);
        memoService.deleteMemo(id);
        return id;
    }

}
