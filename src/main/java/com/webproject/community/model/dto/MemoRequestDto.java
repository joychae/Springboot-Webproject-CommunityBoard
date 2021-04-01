package com.webproject.community.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoRequestDto {

    private Long userId;
    private String title;
    private String created_by;
    private String contents;
}
