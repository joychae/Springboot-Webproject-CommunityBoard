package com.webproject.community.service;

import com.webproject.community.exception.InvalidMemoIdException;
import com.webproject.community.exception.InvalidUserIdException;
import com.webproject.community.model.dto.KakaoUserInfo;
import com.webproject.community.model.dto.SignupRequestDto;
import com.webproject.community.model.entity.Memo;
import com.webproject.community.model.entity.User;
import com.webproject.community.repository.UserRepository;
import com.webproject.community.security.UserDetailsImpl;
import com.webproject.community.security.kakao.KakaoOAuth2;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final KakaoOAuth2 kakaoOAuth2;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    // 1. User Entity 를 Memo, Comment Entity 와 연결시켜주는 사전작업, UserService에 구현!
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new InvalidUserIdException());
    }

    // 2. 일반 회원가입 기능을 처리하는 메소드
    public void registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();

        // 사용자 ID에 알파벳과 숫자만 포함시키기 위한 정규표현식 구현
        Pattern usernamePattern = Pattern.compile("(^[a-zA-Z0-9]*$)");
        Matcher usernameMatcher = usernamePattern.matcher(requestDto.getUsername());

        // 회원 ID 중복 확인
        // 어떤 때 List 대신 Optional이 쓰이는 지 공부해놓을 필요가 있음
        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID가 존재합니다.");
        } else if (requestDto.getUsername().length() < 3) {
            throw new IllegalArgumentException("사용자 ID는 최소 3자 이상이어야 합니다");
        } else if (usernameMatcher.find() == false) {
            throw new IllegalArgumentException("사용자 ID는 알파벳 대소문자와 숫자만 사용가능합니다");
        } else if (requestDto.getPassword().length() < 4) {
            throw new IllegalArgumentException("비밀번호는 최소 4자 이상이어야 합니다");
        } else if (requestDto.getPassword().contains(requestDto.getUsername())) {
            throw new IllegalArgumentException("비밀번호에 사용자 ID가 포함되지 않아야 합니다");
        } else if (!(requestDto.getPassword().equals(requestDto.getPwcheck()))) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다");
        }

        //패스워드 인코딩
        String password = passwordEncoder.encode(requestDto.getPassword());
        // 별다른 처리가 필요없는 이메일 값 가져오기
        String email = requestDto.getEmail();

        // user 객체를 하나 생성해 DB에 저장하기
        User user = new User(username, password, email);
        userRepository.save(user);
    }

    // 3. 카카오 로그인 기능을 처리하는 메소드
    public void kakaoLogin(String authorizedCode) {
        // 카카오 OAuth2 를 통해 카카오 사용자 정보 조회
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(authorizedCode);
        Long kakaoId = userInfo.getId();
        String nickname = userInfo.getNickname();
        String email = userInfo.getEmail();

        // DB 에 중복된 Kakao Id 가 있는지 확인
        User kakaoUser = userRepository.findByKakaoId(kakaoId)
                .orElse(null);

        if (kakaoUser == null) {
            // 카카오 이메일과 동일한 이메일을 가진 회원이 있는지 확인
            User sameEmailUser = userRepository.findByEmail(email).orElse(null);
            if (sameEmailUser != null) {
                kakaoUser = sameEmailUser;
                // 카카오 이메일과 동일한 이메일 회원이 있는 경우
                // 카카오 Id 를 회원정보에 저장
                kakaoUser.setKakaoId(kakaoId);
                userRepository.save(kakaoUser);

            } else {
                // 카카오 정보로 회원가입
                // username = 카카오 nickname
                String username = nickname;
                // password = 카카오 Id + ADMIN TOKEN
                String password = kakaoId + ADMIN_TOKEN;
                // 패스워드 인코딩
                String encodedPassword = passwordEncoder.encode(password);

                kakaoUser = new User(username, encodedPassword, email, kakaoId);
                userRepository.save(kakaoUser);
            }
        }

        // 강제 로그인 처리
        UserDetailsImpl userDetails = new UserDetailsImpl(kakaoUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
