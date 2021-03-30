package com.webproject.community.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoRequestDto {
    private String title;
    private String usernameId;
    private String contents;
}
