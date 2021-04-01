package com.webproject.community.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentRequestDto {

    private Long userId;
    private Long memoId;
    private String content;

}
