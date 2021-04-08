
package com.webproject.community.controller;

import com.webproject.community.security.UserDetailsImpl;
import com.webproject.community.service.CommentService;
import com.webproject.community.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    // 홈 페이지 index.html 매핑
    @GetMapping("/")
    public String homePage(@PageableDefault Pageable pageable, @AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("allmemolist", memoService.getAllMemos(pageable));
        // model.addAttribute("query", query);
        if (userDetails != null) {
            model.addAttribute("userName", userDetails.getUser().getUsername());
            Long accountId = userDetails.getUser().getId();
            model.addAttribute("accountId", accountId);
        }
        if (userDetails == null) {
            model.addAttribute("message", "로그인이 필요한 기능입니다.");
        }
        return "index";
    }

    // 게시글 작성 페이지 write.html 매핑
    @GetMapping("/post/write")
    public String memoWrite() {
        return "write";
    }

    // 게시글 상세보기 페이지 post.html 매핑
    // MemoService 클래스 내 getEachMemo 를 통해 id 값에 해당하는 memo 객체를 반환 받는다.
    // 이를 타임리프 모델로 추가하고, post html 에서 th:text = "memo.title" 과 같은 형태로 작업한다.
    @GetMapping("/post/read/{memoId}")
    public String getEachMemo(@PathVariable Long memoId, @AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("memo", memoService.getEachMemo(memoId));
        model.addAttribute("commentlist", commentService.getComments(memoId));
        if (userDetails != null) {
            model.addAttribute("user", userDetails.getUser());
            model.addAttribute("userName", userDetails.getUser().getUsername());
            model.addAttribute("loginUsercheck", userDetails.getUser().getUsername());
        } else {
            model.addAttribute("userName", " ");
        }
        return "post";
    }

    // 마이페이지 mapage.html 매핑
    @GetMapping("/post/user/{accountId}")
    public String getMemoByUser(@PathVariable Long accountId, @AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("memolist", memoService.getMemoByUser(accountId));
            model.addAttribute("userName", userDetails.getUser().getUsername());
        }
        return "mypage";
    }

}