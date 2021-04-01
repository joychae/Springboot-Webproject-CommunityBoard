package com.webproject.community.model.entity;

import com.webproject.community.model.Timestamped;
import com.webproject.community.model.dto.CommentRequestDto;
import com.webproject.community.service.MemoService;
import com.webproject.community.service.UserService;
import lombok.*;

import javax.persistence.*;

// Entity Table에는 최대한 멤버변수 선언, 생성자, update 로직만 들어가도록 하였다.
@Entity
@Table(name = "COMMENTS")
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID가 자동으로 생성 및 증가합니다
    @Column(name = "ID")
    private Long id;

    // @ManyToOne을 이용한 Entity Table 연관관계 설정 -> 데이터 삽입 시 제약조건으로 작용한다.
    // 장점1 : 유효성 없는 USER_ID, MEMO_ID를 가지고 있는 데이터는 아예 DB에 들어가지 않도록 거르는 장치이다.
    // 장점2 : USER_ID, MEMO_ID를 가진 데이터가 삭제될 경우, 삭제된 데이터와 연관관계가 있는 데이터를 자동으로 삭제시켜준다. -> 유효성 검사 자동
    // 해당 Comment Entity는 하나의 user 객체와 하나의 memo 객체에 연결된다.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MEMO_ID")
    private Memo memo;

    @NonNull
    @Column(name = "CONTENT")
    private String content;

    
    public Comment (CommentRequestDto requestDto, MemoService memoService, UserService userService) {
        this.memo = memoService.findById(requestDto.getMemoId());
        this.user = userService.findById(requestDto.getUserId());
        this.content = requestDto.getContent();
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }

}
