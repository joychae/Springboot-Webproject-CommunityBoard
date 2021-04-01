package com.webproject.community.model.entity;

import com.webproject.community.model.Timestamped;
import com.webproject.community.model.dto.MemoRequestDto;
import com.webproject.community.service.UserService;
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
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(nullable = false) //DB Table에 열을 추가하며, 할당한 항목이 반드시 값을 가지도록 합니다
    private String title;

    @Column(nullable = false)
    private String created_by;

    @Column(nullable = false)
    private String contents;

    public Memo (MemoRequestDto requestDto, UserService userService) {
        this.user = userService.findById(requestDto.getUserId()); // UserService 에서 User Id 값으로 User 객체 찾아서 넣어주기
        this.title = requestDto.getTitle();
        this.created_by = requestDto.getCreated_by();
        this.contents = requestDto.getContents();
    }
}
