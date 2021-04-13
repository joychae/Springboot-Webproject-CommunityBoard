SpringBoot WebProject : CommunityBoard  
=====================

참여인원 : 1명 (채수연)  
프로젝트 url: http://joychae.shop  
- 04.01 처음 카카오 소셜 로그인 시, 이메일 정보 제공 동의에 체크하지 않으면 해당 아이디로는 영구히 카카오 소셜 로그인 기능을 이용하지 못하는 문제 발생
- 04.02 AWS EC2를 이용해 서버 배포 시, 해외 시각을 기준으로 게시글 수정일자가 표기됨
- 04.08 게시글 페이지네이션 기능 추가

</br>

개발 언어
---------
- Backend: Java 8
- Frontend: html, css, javascript (html, css는 부트스트랩을 활용하였습니다.)


</br>

개발 환경
---------
- Java: JDK 1.8.0  
- IDE: IntelliJ IDEA 2020.3.3 x64  
- Build Management: Gradle  
- Framework: SpringBoot  
> - ORM: Spring Data JPA  
> - View Template Engine: thymeleaf  
> - Sub-Framework: Spring security  
> - Test-Framework: junit5


</br>

폴더 구조
---------
```
main
java
com.webproject.community
├─ controller
│  └─ CommentController.java
│  └─ HomeController.java
│  └─ MemoController.java
│  └─ UserController.java
├─ model
│  ├─ dto
│  │  └─ CommentRequestDto.java
│  │  └─ KakaoInfo.java
│  │  └─ MemoRequestDto.java
│  │  └─ SignupRequestDto.java
│  ├─ entity
│  │  └─ Comment.java
│  │  └─ Memo.java
│  │  └─ User.java
│  └─ Timestamped.java
│  │  
├─ repository
│  └─ CommentRepository.java
│  └─ MemoRepository.java
│  └─ UserRepository.java
│  │  
├─ security
│  ├─ kakao
│  │  └─ KakaoOAuth2
│  └─ UserDetailsImpl.java
│  └─ UserDetailsImplService.java
│  └─ WebSecurityConfig.java
│  │ 
├─ service
│  └─ CommentService.java
│  └─ MemoService.java
│  └─ UserService.java
│  │ 
└─  CommunityApplication  

resources
├─ css
│  │  └─ style.css
│  │ 
│  └─ basic.js
│  └─ index.html
│  └─ login.html
│  └─ mypage.html
│  └─ signup.html
└─ └─ write.html
```


</br>

기능 소개
--------
- 회원가입 페이지  
> - 아이디, 비밀번호, 비밀번호 확인, 이메일 기입란과 회원가입 버튼이 존재  
> - 아이디는 최소 3자 이상으로 구성, 알파벳 대소문자, 숫자로만 구성 - Exception의 경우 클라이언트에 에러 메시지가 뜨며 회원가입 실패  
> - 비밀번호는 최소 4자 이상으로 구성, 아이디와 같은 값이 포함되지 않아야 한다는 조건 - Exception의 경우 클라이언트에 에러 메시지가 뜨며 회원가입 실패  
> - 비밀번호 확인은 비밀번호와 일치해야 한다는 조건 - Exception의 경우 클라이언트에 에러 메시지가 뜨며 회원가입 실패  
> - 입력한 아이디가 데이터베이스에 존재하지 않는 아이디여야 한다는 조건 - Exception의 경우 클라이언트에 에러 메시지가 뜨며 회원가입 실패 


</br>

- 로그인 페이지
> - 카카오 소셜 로그인 기능 구현
> - 로그인 성공 시 전체 게시글 목록 조회 페이지로 이동


</br>

- 로그인 검사
> - 회원가입 페이지, 로그인 페이지, 게시글 목록 조회 페이지, 게시글 상세 조회 페이지를 제외한 경우 로그인 한 경우만 이용 가능
> - 올바르지 않은 경로로 접속 시 로그인페이지로 자동 이동
> - 로그인을 한 사용자의 경우, "회원가입"과 "로그인" 버튼 숨기기 + "마이페이지"와 "로그아웃" 버튼 나타내기
> - 로그인을 하지 않은 사용자가 "글 작성하기", "댓글 작성하기" 버튼을 누르면 "로그인이 필요한 기능입니다"라는 알림창이 뜨며 로그인 페이지로 이동
> - 홈 화면의 "로그아웃" 버튼을 누르면 로그아웃 후 홈 화면으로 리다이렉트


</br>

- 게시글 조회 페이지
> - DB에 있는 모든 게시글을 불러와 목록에 작성일 기준 최신순으로 게시 (게시글 제목, 작성자, 작성일(수정일))
> - 게시글 목록의 게시글을 누르면 해당 게시글의 내용, 댓글을 볼 수 있는 상세 페이지로 이동
> - 게시글 목록의 작성자 링크를 누르면 해당 게시글을 작성한 작성자가 작성했던 모든 게시글을 볼 수 있는 페이지로 이동
> - "글 작성하기" 버튼을 누르면 게시글 제목과 내용을 작성할 수 있는 페이지로 이동
> - "마이페이지" 버튼을 누르면 로그인한 사용자가 작성했던 모든 게시글을 볼 수 있는 페이지로 이동


</br>

- 게시글 상세 조회 페이지
> - 게시글의 제목, 작성자, 수정일, 내용, 댓글내용을 게시 - 로그인하지 않은 사용자는 조회만 가능, 댓글 기입은 로그인한 사용자만 가능
> - 댓글 내용은 작성일 기준 최신순으로 게시
> - 댓글 내용을 입력하고 댓글 작성 버튼을 누르면 작성한 댓글이 댓글 목록에 추가
> - 본인이 작성한 댓글에만 "댓글 수정하기", "댓글 삭제하기" 버튼이 보이도록 함
> - "댓글 수정하기" 버튼을 누를 시, 이전에 입력했던 댓글 내용이 기본값으로 들어간 textarea가 팝업, 수정내용 기입 후 다시 "댓글 수정하기" 버튼을 누르면 수정한 내용이 저장된 후 댓글 목록에 게시
> - "댓글 삭제하기" 버튼을 누를 시, "정말로 삭제하기겠습니까?" 메세지를 띄우고 확인/취소 버튼 중 "확인"버튼을 누를 경우 목록에서 해당 댓글을 삭제


</br>

- 회원가입 테스트 코드 작성
> - 회원 가입 시 아이디와 패스워드에 적용한 조건을 검사하는 테스트 코드를 작성


</br>

Entity Table Structure
----------------------
<img width="658" alt="community_table_structure" src="https://user-images.githubusercontent.com/79817873/113205459-4635ee80-92a9-11eb-818f-5a3edeab7a3b.PNG">


### User
```java
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

    @Column(nullable = false)
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
}
```
### Memo
```java
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
```

### Comment
```java
// Entity Table에는 최대한 멤버변수 선언, 생성자, update 로직만 들어가도록 하였다.
@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID가 자동으로 생성 및 증가한다. IDENTITY를 이용하면 다른 Table의 ID 생성에 영향을 받지 않은 채 증가한다.
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

    // Entity 는 스프링 IoC에서 관리되고 있는 Bean 이 아니므로, MemoService 와 UserService 와 의존관계를 생성자 매개변수를 통해 설정하여야 한다.
    // CommentRequestDto 가 담아온 userId와 memoId 값을 이용해 해당 id 값을 가지고 있는 객체에 담긴 정보를 통채로 가져와서 user, memo 변수에 담아준다.
    public Comment(CommentRequestDto requestDto, MemoService memoService, UserService userService) {
        this.user = userService.findById(requestDto.getUserId());
        this.memo = memoService.findById(requestDto.getMemoId());
        this.content = requestDto.getContent();
    }

    // 댓글 내용 수정을 위한 업데이트 메소드이다.
    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }

}
```


</br>

- 유저, 게시글, 댓글을 관리하는 세 개의 DB Table을 만들어서 관리합니다.  
- 비즈니스 로직은 최대한 Service Layer에서 해결하고, DB Column과 생성자, DB 업데이트 로직까지만 Enitity에서 관리할 수 있도록 하였습니다.  
- 개발 초기에는 @ManyToOne을 이용한 Entity Table 연관관계 설정을 해주지 않고, Dto에서 받아온 UserId와 MemoId 값 들을 바로 변수에 할당하였습니다. Entity안에 User, Memo 객체가 아닌 Long userId와 Long memoId 형태로 받았습니다. 하지만 완성 단계에서 @ManyToOne을 도입해 Entity간의 연관관계를 설정하였습니다. 확실히 Entity Table이 어떻게 연관되어 있는지 가시적으로 보기 편한 게 1차적인 장점이었습니다. 이외의 다른 장점도 많았습니다. 두 번째 장점은 Long userId가 아닌 User 객체로 연결되어 있기 떄문에 연결된 User 객체의 데이터를 가져오기 편리했습니다. 예를 들어 게시글의 작성자 값을 가져오고 싶은 상황이라고 가정해봅시다. Long userId만 가지고 있을 경우, 일일히 User DB TABLE을 탐방하여 userId에 대응하는 username을 찾아야 합니다. 하지만 User 객체를 게시글 DB에 저장시킬 경우, 작성자 값을 찾을 필요 없이 바로 가져올 수 있었다는 점이었습니다. 세 번쨰 장점은 자동으로 유효성 검사가 되는 점이었습니다. 게시글 Dto가 가져온 userId가 유효성이 없는 값인 경우 DB 자체에 게시글 데이터가 저장되지 않습니다. userId 삭제 시 연결된 데이터가 자동 삭제되는 등 편리한 점이 많았습니다.


</br>

# 기능 상세 소개


</br>

회원가입 페이지
---------------

### UserService

```java
    // 일반 회원가입 기능을 처리하는 메소드
    public void registerUser(SignupRequestDto requestDto) {
    
        String username = requestDto.getUsername();
        
        // 회원 ID 중복 확인을 위한 found 생성
        Optional<User> found = userRepository.findByUsername(username);

        // 사용자 ID에 알파벳과 숫자만 포함시키기 위한 정규표현식 구현
        Pattern usernamePattern = Pattern.compile("(^[a-zA-Z0-9]*$)");
        Matcher usernameMatcher = usernamePattern.matcher(requestDto.getUsername());
        
        // 회원가입 조건을 검사하는 로직
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

        // 가입 조건을 통과한 후, 패스워드 인코딩 과정 진행 (UserDeailsServiceImpl에 passwordEncoder.encode 메소드 구현해놓았음)
        String password = passwordEncoder.encode(requestDto.getPassword());
        
        // 별다른 처리가 필요없는 이메일 값 가져오기
        String email = requestDto.getEmail();

        // user 객체를 하나 생성해 DB에 저장하기
        User user = new User(username, password, email);
        userRepository.save(user);
    }
```

- 가입 조건을 확인하는 로직은 User Entity에 구현할 수도 있지만, Entity Table을 최대한 깨끗하게 관리하기 위해 UserService 내의 메소드로 기입 조건을 확인하는 로직을 구현했습니다.
- Entity Table을 깨끗하게 관리하는 이유는 두 가지를 꼽을 수 있겠습니다. 첫째, Entity 테이블에 해당하는 코드만 보고도 연결되어 있는 DB Table Column의 구성 요소를 확인하기 위함입니다. 둘째, 어떠한 매개변수와 Dto를 활용해 생성자를 만드는 지 한눈에 파악하기 위함입니다. 따라서 저는 이번프로젝트에서 Entity 코드에는 Column 내용과 생성자, 업데이트 로직만을 구현하였습니다.


</br>

### UserController

```java
    // 회원 가입을 처리할 url과 signup.html 뷰 매핑
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원가입 창에서 parameter 값을 받아오는 PostMapping Controller
    // 검사 통과 시 회원가입자 정보가 User DB에 저장되고, 홈 화면으로 리다이렉트 된다.
    // 검사 미통과 시 에러 메시지가 반환되고, 이 에러 메시지를 model로 뷰에 전달하여 프론트 회원가입 화면에서 에러 메시지가 표시된다.
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto, Model model) {
        try {
            userService.registerUser(requestDto);
            // userService registerUser 메소드에서 에러를 던지면 에러메시지를 받아서 프론트에 표기해주는 작업
            // 프론트 표기는 thymeleaf 를 활용한다.
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
        return "redirect:/";
    }
```

- UserController에서는 깔끔하게 GetMapping 하나와 PostMapping 하나를 구현하였습니다.
- UserService에서 회원가입 조건에 맞게 검사하여 Controller에 검사 통과시와 미통과시를 나누어 반환값을 돌려줍니다.



</br>

로그인
---------------

### UserDetailsServiceImpl
```java
// UserDetailsService 는 인증로직 서비스 인터페이스이다.
// UserDetailsServiceImpl 는 UserDetailsService 인터페이스를 구현하는 구현 클래스이다.
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

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
```
### UserDetailsImpl
```java
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Long getAccountId() {
        return user.getId();
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
```
- 로그인 로직은 UserDetailsServiceImpl과 UserDetailsImpl에서 진행하고 관리합니다. 
- Spring Security에서 제공하는 UserDetailsService와 UserDetails를 implements하여 사용하였습니다.
- 코드의 자세한 진행 과정은 제가 달아놓은 주석을 참고 부탁드립니다.

</br>

로그인 검사
---------------
### WebSecurityConfig
```java
@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 패스워드 인코드를 위한 Bean 을 스프링 IoC에 등록하는 과정
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                // image 폴더를 login 없이 허용
                .antMatchers("/images/**").permitAll()
                // css 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()
                .antMatchers("/basic.js").permitAll()
                // login없이 허용하는 경로를, 폴더를 지정할 수도 있지만 아래와 같이 도메인명으로 지정할 수도 있음
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/post/read/**").permitAll()
                // 코멘트 여는건 테스트!
                .antMatchers("/comment").permitAll()
                .antMatchers("/api/memos/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                // 그 외 모든 요청은 인증과정 필요
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .permitAll();

    }
}
```
- Spring Secutiry 클래스인 WebSecurityConfigurerAdapter을 상속받아 Override하여 로그인을 하지 않은 사용자에게 허용하는 url을 커스터마이징 해주었습니다.
- 허용하고자 하는 파일, url을 .antMatchers("/").permitAll()의 형태로 .anyRequest().authenticated() 위에 기입해주면 됩니다.
- .anyRequest().authenticated() 위에 permitAll()로 허용되지 않은 url로 로그인을 하지 않은 사용자가 접속할 경우, 로그인 페이지로 자동 이동됩니다.
- .logoutUrl("/user/login")으로 로그아웃을 진행할 url을 할당하였습니다. 이후 logoutSuccesUrl("/")을 추가해 로그아웃 성공 후 홈 화면으로 리다이렉트 되도록 만들어 주었습니다.


</br>

### HomeCotroller
```java
    // 홈 페이지 index.html 매핑
    @GetMapping("/")
    public String homePage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("allmemolist", memoService.getAllMemos());
        // model.addAttribute("query", query);
        if (userDetails != null) {
            model.addAttribute("userName", userDetails.getUser().getUsername());
            Long accountId = userDetails.getUser().getId();
            model.addAttribute("accountId", accountId);
        }
        if (userDetails == null) {
            model.addAttribute("message", "로그인이 필요한 기능입니다.");
        }
        return "index";
    }
```
### index.html
```html
        <button th:if="${userName} == null" type="button" class="btn btn-secondary btn-sm float-right"
                onclick="location.href='/user/login'" style="margin-left: 10px; margin-bottom: 20px">로그인
        </button>
        <button th:if="${userName} != null" type="button" class="btn btn-secondary btn-sm float-right"
                onclick="location.href='/user/logout'" style="margin-left: 10px; margin-bottom: 20px">로그아웃
        </button>
        <button th:if="${userName} == null" type="button" class="btn btn-secondary btn-sm float-right"
                onclick="location.href='/user/signup'">회원가입
        </button>
        <button th:if="${userName} != null" th:text="${userName}+' 마이페이지'"
                th:onclick="|location.href='/post/user/${accountId}'|" type="button"
                class="btn btn-secondary btn-sm float-right">
        </button>
```
- 로그인 한 사용자의 정보를 담고 있는 UserDeatailsImpl 클래스를 매 변수로 받아 로그인한 사용자의 아이디를 받아왔습니다.
- 비로그인 사용자라면 userDeatils 값이 null 일 테니 userName에 해당하는 attribute model도 null 입니다. 이를 활용하여 타임리프 조건문으로 각 조건에 해당하는 버튼을 만들었습니다.


</br>

게시글 조회 페이지
---------------

### MemoRepository
```java
public interface MemoRepository extends JpaRepository<Memo, Long> {

    // 게시글 모두 불러온 후, 수정일을 기준으로 최신순 정렬하는 명령문 입니다.
    List<Memo> findAllByOrderByModifiedAtDesc();

    // 사용자 아이디로 게시글 목록을 찾은 후, 수정일을 기준으로 최신순 정렬하는 명령문 입니다.
    List<Memo> findByUserIdOrderByModifiedAtDesc(Long id);

    // 게시글을 검색하는 명령문 입니다.
    List<Memo> findByTitleContaining(String keyword);

}
```
### MemoService
```java
    // 게시글 전체 조회 기능
    public List<Memo> getAllMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }
```
### HomeController
```java
    @GetMapping("/")
    public String homePage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("allmemolist", memoService.getAllMemos());
        // model.addAttribute("query", query);
        if (userDetails != null) {
            model.addAttribute("userName", userDetails.getUser().getUsername());
            Long accountId = userDetails.getUser().getId();
            model.addAttribute("accountId", accountId);
        }
        if (userDetails == null) {
            model.addAttribute("message", "로그인이 필요한 기능입니다.");
        }
        return "index";
    }
```
**DB에 있는 모든 게시글을 불러와 목록에 작성일 기준 최신순으로 게시 (게시글 제목, 작성자, 작성일(수정일))**
- Spting Data JPA에서 제공하는 명령문 문법에 맞게 작성하여, 손쉽게 DB에서 원하는 기준으로 값을 찾고 원하는 기준으로 정렬할 수 있었습니다.
- MemoService 단계를 거치지 않고 바로 HomeController에서 MemoRepository를 DI 받을 수 있지만, 저는 최대한 Controller와 Repository의 직접 연결을 피하였습니다. 전체 코드를 보시면 아시겠지만 제가 작성한 네 개의 Controller는 전부 Repository Layer와 직접 의존관계를 맺지 않고 Service Layer를 거쳐 의존 관계가 설정됩니다. 이렇게 코드를 구현했을 경우 Service Layer만 보았을 때 비즈니스 로직이 무엇인지 Controller까지 꼼꼼히 보지 않아도 빠짐없이 파악할 수 있다는 장점이 있습니다.
- 클라이언트에서 리스트 형태의 데이터를 View로 내려 받을 때는 th:each 라는 타임리프 반복문을 사용해 화면에 나타나게 하였습니다. 번거로운 java script 반복문을 쓰지 않아 코드가 좀 더 단순해졌습니다.


</br>

### index.html
```html
        <!--        게시글 조회 테이블-->
        <table class="table" style="cursor: pointer;">
            <thead>
            <tr>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">작성일</th>
            </tr>
            </thead>
            <tbody th:each="allmemo: ${allmemolist}">
            <tr th:onclick="|location.href = '/post/read/${allmemo.id}'|">
                <th th:text="${allmemo.title}" scope="row"></th>
                <td>
                    <a th:text="${allmemo.user.username}" th:href="'/post/user/'+${allmemo.user.id}"></a>
                </td>
                <td th:text="${allmemo.modifiedAt}"></td>
            </tr>
            </tbody>
        </table>
```
**게시글 목록의 게시글을 누르면 해당 게시글의 내용, 댓글을 볼 수 있는 상세 페이지로 이동**
**게시글 목록의 작성자 링크를 누르면 해당 게시글을 작성한 작성자가 작성했던 모든 게시글을 볼 수 있는 페이지로 이동**
- 해당 게시글 목록을 클릭하면 타임리프를 활용해 onclick 값을 부여한 링크로 자동 이동되도록 설정하였습니다. ${allmemo.id}는 해당 memo의 id 값을 가져오는 수식입니다. "/" url을 담당하는 Controller에서 allmemolist를 model attribute 값으로 내려주었고, 이를 th:each에서 allmemo 값으로 받았기 때문에 allmemo.id는 각 메모 객체의 id 값을 가리킵니다.
- ./post/read/{memoId}라는 url에서 게시글 상세 정보를 view에 내려주도록 HomeCotroller에서 관리하고 있습니다.
- 작성자가 작성했던 모든 게시글을 볼 수 있는 페이지로의 이동 역시 th:href로 각 user의 id 값을 경로에 넣어 게시글 상세보기 로직과 비슷하게 구현하였습니다.


</br>

게시글 상세 조회 페이지
---------------

### HomeController
```java
    // 게시글 상세보기 페이지 post.html 매핑
    // MemoService 클래스 내 getEachMemo 를 통해 id 값에 해당하는 memo 객체를 반환 받는다.
    // 이를 타임리프 모델로 추가하고, post html 에서 th:text = "memo.title" 과 같은 형태로 작업한다.
    
    @GetMapping("/post/read/{memoId}")
    public String getEachMemo(@PathVariable Long memoId, @AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("memo", memoService.getEachMemo(memoId));
        model.addAttribute("commentlist", commentService.getComments(memoId));
        if (userDetails != null) {
            model.addAttribute("user", userDetails.getUser());
            model.addAttribute("userName", userDetails.getUser().getUsername());
            model.addAttribute("loginUsercheck", userDetails.getUser().getUsername());
        } else {
            model.addAttribute("userName", " ");
        }
        return "post";
    }
```
### CommentController
```java
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글을 작성하는 기능입니다.
    @ResponseBody
    @PostMapping("/post/read/{memoId}/comment")
    public Comment createComment(@PathVariable Long memoId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        requestDto.setMemoId(memoId);
        requestDto.setUserId(userDetails.getUser().getId());
        Comment comment = commentService.createComment(requestDto);
        return comment;
    }

    // 댓글을 수정하는 기능입니다.
    @ResponseBody
    @PutMapping("/api/comments/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        commentService.commentUpdate(id, commentRequestDto);
        return id;
    }

    // 댓글을 삭제하는 기능입니다.
    @ResponseBody
    @DeleteMapping("/api/comments/{id}")
    public Long deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }
}
```

### post.html
```html
<body>
<div id="posting-each">
    <div class="card">
        <h5 th:text="${memo.title}" class="card-header"></h5>
        <!--    게시글 내용 조회 -->
        <div class="card-body">
            <h5 th:text="${memo.user.username}" class="card-title"></h5>
            <p th:text="${memo.modifiedAt}" class="card-text"></p>
            <p th:text="${memo.content}" class="card-text"></p>

            <!--        댓글 작성 -->
            <div class="input-group mb-3">
                <input type="text" class="form-control" id="comment" placeholder="댓글을 남겨주세요"
                       aria-label="Recipient's username"
                       aria-describedby="basic-addon2">
                <div class="input-group-append">
                    <script type="text/javascript" th:inline="javascript">
                        /*<![CDATA[*/
                        function loginalert() {
                            alert("로그인이 필요한 기능입니다");
                            location.href = '/user/login';
                        }

                        /*]]>*/
                    </script>
                    <!--                    로그인을 안한 유저를 위한 버튼-->
                    <button th:if="${loginUsercheck} == null" th:onclick="loginalert()" class="btn btn-outline-secondary"
                            type="button">댓글 작성하기
                    </button>
                    <!--                    로그인을 한 유저를 위한 버튼-->
                    <button th:if="${loginUsercheck} != null" class="btn btn-outline-secondary" type="button"
                            onclick="writeComment()">댓글 작성하기
                    </button>
                </div>
            </div>

            <!--        댓글 조회 -->
            <div id="comment-area">
                <div th:each="comment: ${commentlist}" class="card">
                    <div class="card-body">
                        <h6 th:text="${comment.user.username}" class="card-subtitle mb-2 text-muted"></h6>
                        <p th:text="${comment.modifiedAt}" class="card-text"></p>
                        <p th:text="${comment.content}" th:id="${comment.id}+'-content'" class="card-text"></p>
                        <textarea th:id="${comment.id}+'-editarea'" style="display: none" cols="30"></textarea>
                        <a th:if="${userName.equals(comment.user.username)}" th:onclick="'editComment('+${comment.id}+')'"
                           th:id="${comment.id}+'-edit'" href="#" class="card-link">댓글 수정하기</a>
                        <a th:if="${userName.equals(comment.user.username)}" th:onclick="'submitEdit('+${comment.id}+')'"
                           th:id="${comment.id}+'-submit'" style="display: none" href="#" class="card-link">댓글 수정하기</a>
                        <a th:if="${userName.equals(comment.user.username)}"
                           th:onclick="'deleteComment('+${comment.id}+')'" th:id="${comment.id}+'-delete'" href="#"
                           class="card-link">댓글 삭제하기</a>
                    </div>
                </div>
            </div>
            <button type="button" class="btn btn-primary btn-sm" style="margin-top:20px;"
                    onclick="location.href='/'">목록으로 돌아가기
            </button>
        </div>
    </div>
</div>
</body>
```
- 게시글 상세 조회 페이지입니다. /post/read/{memoId}에 해당하는 view를 내려주는 API를 HomeController에서 관리하고 있습니다.
- 댓글의 경우 Get 이외의 POST, PUT, DELETE 는 View가 아닌 JSON 형태로 ConmmentController에서 관리하도록 하였습니다.
- 개인적으로 JSON 형태로 데이터가 오고 가는 것을 선호합니다 (어떤 데이터가 오고 갔는지 좀 더 확실히 판단할 수 있음). 하지만 View형태로 데이터를 반환하고 이를 타임리프로 받아 사용하는 것이 압도적으로 편리해 Get API를 View 형태로 반환해주다보니, CRUD 데이터 반환 형태를 통일하지 못했다는 아쉬움이 남은 프로젝트였습니다.

