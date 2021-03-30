package com.webproject.community.model.entity;

import com.webproject.community.model.Timestamped;
import com.webproject.community.model.dto.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "COMMENTS")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID가 자동으로 생성 및 증가합니다
    @Column(name = "COMMENTS_NO")
    private Long id;

    @NonNull
    @Column(name="MEMO_ID")
    private Long memoId;

    @NonNull
    @Column(name="USER_ID")
    private Long userId;

    @NonNull
    @Column(name="CREATED_BY")
    private String created_by;

    @NonNull
    @Column(name = "CONTENT")
    private String content;


    @NonNull
    @Column(name = "DEL_YN")
    @ColumnDefault(value = "false")
    private Boolean deleted;

    public Comment (CommentRequestDto requestDto) {
        this.memoId = requestDto.getMemoId();
        this.userId = requestDto.getUserId();
        this.created_by = requestDto.getCreated_by();
        this.content = requestDto.getContent();
        this.deleted = false;
    }

}
