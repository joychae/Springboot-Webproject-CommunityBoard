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

1. 회원가입 페이지
-----------------

