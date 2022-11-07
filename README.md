# OfficeSharingPlatform

## Repository for Deploy Project

### BE : 오령기, 최한성, 이동훈

### FE : 이찬형

### 개발 일정

```mermaid
gantt
dateFormat MM-DD
title OfficeSharingPlatform : BE

    section 공통
    요구사항 명세서 분석      :chap1, 10-21, 1d
    구현 기능 분석          :chap2, 10-21, 1d
    클래스 다이어그램 작성    :chap3, 10-21, 1d
    테이블 설계            :chap4, 10-21, 4d
    변수 이름 명세          :chap5, 10-24, 1d
    IntelliJ 환경 설정     :chap6, 10-24, 1d

    section 메인플랫폼
    회원가입                :chap1, 10-25, 1d
    로그인                 :chap2, 10-26, 3d
    스프링 시큐리티 적용       :chap3, 10-27, 6d
    CSRF -> JWT 방식 변경   :chap4, 10-27, 6d
    MariaDB, MyBatis 설정  :chap5, 11-02, 1d
    예약 로직 구현           :chap6, 11-03, 4d
    마이페이지               :chap7, 11-05, 1d
    Jwt 토큰 복호화 로직 구현  :chap8, 11-06, 1d


```

```mermaid
gantt
dateFormat MM-DD
title OfficeSharingPlatform : FE

    section 공통
    요구사항 명세서 분석      :chap1, 10-21, 1d
    구현 기능 분석          :chap2, 10-21, 1d
    클래스 다이어그램 작성    :chap3, 10-21, 1d
    UI설계, 페이지 흐름 구성          :chap4, 10-21, 2d
    변수 이름 명세          :chap5, 10-24, 1d


    section 메인플랫폼
    메인페이지 kakomaps     : chap1, 10-24, 1d
    회원가입                :chap2, 10-25, 1d
    로그인                 :chap3, 10-26, 1d
    페이지 구성            :chap4, 10-27, 1d
    예약 페이지            :chap5, 10-28, 7d
    마이 페이지            :chap6, 11-5, 1d


```
