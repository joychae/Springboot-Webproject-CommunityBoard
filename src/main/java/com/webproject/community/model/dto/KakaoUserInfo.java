package com.webproject.community.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoUserInfo {

    // 주의! email이랑 nickname 순서 바꾸면, 카카오 닉네임이 email 변수에 담기게 됩니다.
    Long id;
    String email;
    String nickname;

}
