package com.webproject.community.model.entity;

import com.webproject.community.model.Timestamped;
import com.webproject.community.model.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Memo extends Timestamped {
    // createdAt, modifiedAt, title, usernameId, contents 를 멤버변수로 가지는 entity 클래스 입니다.
    // @NoArgsConstructor 로 생성자를 만들어 주었습니다.

    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID가 자동으로 생성 및 증가합니다
    @Id
    @Column(name = "memoId")
    private Long memoId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false) //DB Table에 열을 추가하며, 할당한 항목이 반드시 값을 가지도록 합니다
    private String title;

    @Column(nullable = false)
    private String usernameId;

    @Column(nullable = false)
    private String contents;

    public Memo (MemoRequestDto requestDto, Long userId) {
        this.userId = userId;
        this.title = requestDto.getTitle();
        this.usernameId = requestDto.getUsernameId();
        this.contents = requestDto.getContents();
    }
}
