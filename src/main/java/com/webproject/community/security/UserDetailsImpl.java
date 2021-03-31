package com.webproject.community.security;

import com.webproject.community.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    // 이 부분부터 isEnabled 를 오버라이딩 메소드로 만들어주는 데까지는, 인증을 위한 필수 필드값들이다.
    // 대부분 기본값이 false 이거나 null 이지만 이를 true 로 바꾸어 인증 과정을 진행한다.

    // 왜 이 부분 추가하기 전에는 왜 implements UserDetails 에 빨간 줄 떴지?
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Long getAccountId() {
        return user.getAccountId();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
