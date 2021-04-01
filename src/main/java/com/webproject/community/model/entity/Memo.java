package com.webproject.community.model.entity;

import com.webproject.community.model.Timestamped;
import com.webproject.community.model.dto.MemoRequestDto;
import com.webproject.community.service.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Memo extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Long id;

    // @ManyToOne을 이용한 Entity Table 연관관계 설정 -> 데이터 삽입 시 제약조건으로 작용한다.
    // 장점1 : 유효성 없는 USER_ID 를 가지고 있는 데이터는 아예 DB에 들어가지 않도록 거르는 장치이다.
    // 장점2 : USER_ID 를 가진 데이터가 삭제될 경우, 삭제된 데이터와 연관관계가 있는 데이터를 자동으로 삭제시켜준다. -> 유효성 검사 자동
    // 해당 Comment Entity는 하나의 user 객체와 연결된다.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @NonNull
    @Column(name = "TITLE") //DB Table에 열을 추가하며, 할당한 항목이 반드시 값을 가지도록 합니다
    private String title;

    @NonNull
    @Column(name = "CONTENT")
    private String content;

    public Memo (MemoRequestDto requestDto, UserService userService) {
        this.user = userService.findById(requestDto.getUserId()); // UserService 에서 User Id 값으로 User 객체 찾아서 넣어주기
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
