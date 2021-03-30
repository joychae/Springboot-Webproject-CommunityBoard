package com.webproject.community.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoUserInfo {
    Long id;
    String email;
    String nickname;
}
