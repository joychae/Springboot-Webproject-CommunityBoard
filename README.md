SpringBoot WebProject : CommunityBoard  
=====================


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


</br>

- 유저, 게시글, 댓글을 관리하는 세 개의 DB Table을 만들어서 관리합니다.  
- 비즈니스 로직은 최대한 Service Layer에서 해결하고, DB Column과 생성자, DB 업데이트 로직까지만 Enitity에서 관리할 수 있도록 하였습니다.  


</br>

# 기능 상세 소개


</br>

회원가입 페이지
---------------

<UserService>
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

<UserController>
```java
    // 회원 가입을 처리할 url과 signup.html 뷰 매핑
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리, memoService에서 회원가입 조건을 검사한다.
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
- UserController에서는 깔끔하게 GetMapping 하나와 PostMapping 하나를 구현하였다.
- UserService에서 회원가입 조건에 맞게 검사하여 Controller에 검사 통과시와 미통과시를 나누어 반환값을 돌려준다.















