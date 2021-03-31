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
> - 로그인을 한 사용자의 경우, 회원가입과 로그인 버튼 숨기기 + 마이페이지와 로그아웃 버튼 나타내기


</br>

Entity Table Structure
----------------------
<img width="658" alt="community_table_structure" src="https://user-images.githubusercontent.com/79817873/113205459-4635ee80-92a9-11eb-818f-5a3edeab7a3b.PNG">


</br>
- 유저, 게시글, 댓글을 관리하는 세 개의 DB Table을 만들어서 관리한다.  
- 비즈니스 로직은 최대한 Service Layer에서 해결하고, DB Column과 생성자, DB 업데이트 로직까지만 Enitity에서 관리할 수 있도록 하였다.


