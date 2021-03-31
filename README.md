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
> dfdf


</br>

Entity Table Structure
----------------------
<img width="658" alt="community_table_structure" src="https://user-images.githubusercontent.com/79817873/113205459-4635ee80-92a9-11eb-818f-5a3edeab7a3b.PNG">


</br>
- 유저, 게시글, 댓글을 관리하는 세 개의 DB Table을 만들어서 관리한다.
- 비즈니스 로직은 최대한 Service Layer에서 해결하고, DB Column과 생성자, DB 업데이트 로직까지만 Enitity에서 관리할 수 있도록 하였다.


