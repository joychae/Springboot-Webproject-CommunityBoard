package com.webproject.community.controller;

import com.webproject.community.security.UserDetailsImpl;
import com.webproject.community.service.CommentService;
import com.webproject.community.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemoService memoService;
    private final CommentService commentService;

    @GetMapping("/")
    public String homePage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("userName", userDetails.getUser().getUsername());
        }
        return "index";
    }

    @GetMapping("/post/write")
    public String memoWrite() {
        return "write";
    }

    // 게시글 상세보기를 구현한다.
    // MemoService 클래스 내 getEachMemo 를 통해 id 값에 해당하는 memo 객체를 반환 받는다.
    // 이를 타임리프 모델로 추가하고, post html 에서 th:text = "memo.title" 과 같은 형태로 작업한다.
    @GetMapping("/post/read/{memoId}")
    public String getEachMemo(@PathVariable Long memoId, @AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("memo", memoService.getEachMemo(memoId));
        model.addAttribute("commentlist", commentService.getComments(memoId));
        if (userDetails != null) {
            model.addAttribute("userName", userDetails.getUser().getUsername());
        } else {
            model.addAttribute("userName", " ");
        }
        return "post";
    }
}
