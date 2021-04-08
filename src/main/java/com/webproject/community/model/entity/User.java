package com.webproject.community.model.entity;

import com.webproject.community.model.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String email;

    // 일반 회원가입루트로 회원가입 한 경우 해당 맴버변수 값이 null 이다.
    @Column(nullable = true)
    private Long kakaoId;

    // 카카오 로그인이 아닌 일반 회원가입루트로 회원가입 한 경우 멤버변수 값
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.kakaoId = null;
    }

    // 카카오 로그인루트로 자동 회원가입 한 경우 멤버변수 값
    public User(String username, String password, String email, Long kakaoId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.kakaoId = kakaoId;
    }

    public User(String username, String password, Long kakaoId) {
        this.username = username;
        this.password = password;
        this.email = null;
        this.kakaoId = kakaoId;
    }
}
