package com.webproject.community.service;

import com.webproject.community.model.dto.SignupRequestDto;
import com.webproject.community.model.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Nested
    @DisplayName("회원 가입 시 유저 객체 생성")
    class registerUserTest {
        private Long accountId;
        private String username;
        private String password;
        private String pwcheck;
        private String email;

        @BeforeEach
        public void setup() {
            accountId = 100L;
            username = "joy";
            password = "hh991234";
            pwcheck = "hh991234";
            email = "csy@naver.com";
        }

        @Test
        @DisplayName("정상 케이스")
        public void registerUserTest_Normal() {
            // given
            SignupRequestDto requestDto = new SignupRequestDto(username, password, pwcheck, email);

            // when
            User user = new User(requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail());

            // then
            assertNull(user.getAccountId());
            assertEquals(username, user.getUsername());
            assertEquals(password, user.getPassword());
            assertEquals(email, user.getEmail());
            assertNull(user.getKakaoId());
        }

        @Nested
        @DisplayName("실패 케이스")
        class FailCases {

            @Nested
            @DisplayName("닉네임")
            class username {

                @Test
                @DisplayName("글자수")
                public void fail1() {
                    //given
                    username = "jo";

                    SignupRequestDto requestDto = new SignupRequestDto(username, password, pwcheck, email);

                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        userService.registerUser(requestDto);
                    });

                    assertEquals("사용자 ID는 최소 3자 이상이어야 합니다", exception.getMessage());
                }

                @Test
                @DisplayName("사용글자종류")
                public void fail2() {

                    username = "joy*^";

                    SignupRequestDto requestDto = new SignupRequestDto(username, password, pwcheck, email);

                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        userService.registerUser(requestDto);
                    });

                    assertEquals("사용자 ID는 알파벳 대소문자와 숫자만 사용가능합니다", exception.getMessage());
                }

            }

            @Nested
            @DisplayName("비밀번호")
            class password {

                @Test
                @DisplayName("글자수")
                public void fail1() {

                    username = "julia";
                    password = "123";

                    SignupRequestDto requestDto = new SignupRequestDto(username, password, pwcheck, email);

                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        userService.registerUser(requestDto);
                    });

                    assertEquals("비밀번호는 최소 4자 이상이어야 합니다", exception.getMessage());

                }

                @Test
                @DisplayName("닉네임과 같은 값 포함")
                public void fail2() {

                    username = "julia";
                    password = "julia123";

                    SignupRequestDto requestDto = new SignupRequestDto(username, password, pwcheck, email);

                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        userService.registerUser(requestDto);
                    });

                    assertEquals("비밀번호에 사용자 ID가 포함되지 않아야 합니다", exception.getMessage());

                }
            }

            @Nested
            @DisplayName("비밀번호 확인")
            class pwcheck {

                @Test
                @DisplayName("비밀번호 확인 불일치")
                public void fail1() {

                    username = "julia";
                    pwcheck = "hh99";

                    SignupRequestDto requestDto = new SignupRequestDto(username, password, pwcheck, email);

                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        userService.registerUser(requestDto);
                    });

                    assertEquals("비밀번호와 비밀번호 확인이 일치하지 않습니다", exception.getMessage());

                }

            }

            @Nested
            @DisplayName("중복 사용자 ID 확인")
            class idcheck {

                @Test
                @DisplayName("중복 사용자 ID 확인")
                public void fail1() {

                    String found = "joy";
                    SignupRequestDto requestDto = new SignupRequestDto(username, password, pwcheck, email);

                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        userService.registerUser(requestDto);
                    });

                    assertEquals("중복된 사용자 ID가 존재합니다", exception.getMessage());

                }
            }
        }
    }
}