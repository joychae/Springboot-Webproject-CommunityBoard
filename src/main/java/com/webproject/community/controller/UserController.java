package com.webproject.community.controller;

import com.webproject.community.model.dto.SignupRequestDto;
import com.webproject.community.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원 로그인 페이지
    // thymeleaf 쓸 떄 resolving 오류 나면 무조건 경로 문제다
    // application.properties 에서 경로 /static/ 이라고 지정해두고, html 파일들 images 폴더에 넣어뒀더니 resolving 에러 났었다.
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 로그인 에러 페이지
    @GetMapping("/user/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리, 중복 및 오류 체크
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto, Model model) {
        try {
            userService.registerUser(requestDto);
            // userService registerUser 메소드에서 에러를 던지면 에러메시지를 받아서 프론트에 표기해주는 작업
            // 프론트 표기는 thymeleaf 를 활용한다.
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
        return "redirect:/";
    }

    // 카카오 로그인 및 자동 로그인 처리 되었을 때, index 페이지 반환
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(String code) {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        userService.kakaoLogin(code);
        return "redirect:/";
    }
}
