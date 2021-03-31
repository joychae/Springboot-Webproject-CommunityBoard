SpringBoot WebProject : CommunityBoard  
=====================

개발 언어
---------
- Backend: Java 8
- Frontend: html, css, javascript (html, css는 부트스트랩을 활용하였습니다.)

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





