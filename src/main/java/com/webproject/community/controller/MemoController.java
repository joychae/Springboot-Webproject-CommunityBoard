package com.webproject.community.controller;

import com.webproject.community.model.dto.MemoRequestDto;
import com.webproject.community.model.entity.Memo;
import com.webproject.community.security.UserDetailsImpl;
import com.webproject.community.service.MemoService;
import com.webproject.community.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @GetMapping("/api/memos")
    public List<Memo> getAllMemos() {
        return memoService.getAllMemos();
    }

    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        // 로그인 되어 있는 사용자 정보 가져와서 requestDto에 넣어주기
        requestDto.setUserId(userDetails.getUser().getId());
        Memo memo = memoService.createMemo(requestDto);
        return memo;
    }

}
