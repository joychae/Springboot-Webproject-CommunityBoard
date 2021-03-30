package com.webproject.community.security;

import com.webproject.community.model.entity.User;
import com.webproject.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

// UserDetailsService 는 인증로직 서비스 인터페이스이다.
// UserDetailsServiceImpl 는 UserDetailsService 인터페이스를 구현하는 구현 클래스이다.
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    // login 로직 시작
    // loginId를 이용하여 DB 에서 User 객체를 가져옵니다.
    // User user = userRepository.findByUsername(username);
    // -> 이 부분에서 Repository 를 의존성 주입해 DB 에 다녀와 username 에 맞는 데이터를 가져와서 Security 객체에 담아주는 작업을 한다.
    // User 의 정보를 UserDetailsServiceImpl 에 담아줍니다. 이는 생성자를 이용하는 편이다.
    // UserDetails 인터페이스 구현 클래스의 인스턴스를 리턴해줌으로써 로그인 로직이 완성된다!
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 ID 또는 패스워드를 확인해주세요"));
        return new UserDetailsImpl(user);
    }
}

// 가질 수 있는 의문점, 아이디만으로 객체를 가져오고 그것만으로 UserDetailsImpl 에서 인증값을 true 로 반환하면 비밀번호가 필요없는것 아닌가?
// 답: 비밀번호를 확인하는 로직이 스프링 시큐리티 내부에 있어서 우리 눈에 보이지 않았던 것이다.
// 기준이 되는 데이터는 User 객체에서 받은 데이터이다. 여기에 아이디와 패스워드가 들어있을 것이고, 이는 수정되지 않았다면 회원가입 당시에 입력된 데이터일 터.
// DB 정보를 스프링 시큐리티에서 받아오고 난 후, 사용자 입력 데이터와 비교한다.
// loadUserByUsername 을 호출하여 인증을 마치고, 내부적으로는 UserDetailsImpl 클래스와 패스워드를 passwordEncoder 를 이용하여 처리할 수 있도록 하는 서비스 로직이 존재.
