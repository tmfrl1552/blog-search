# Blog Search
## Overview
오픈 API를 이용한 '블로그 검색' 서비스 입니다. <br>
블로그 검색과 인기 검색어 조회 기능을 사용할 수 있습니다. <br>

* #### 🔗  <a href="https://github.com/tmfrl1552/blog-search/blob/main/app-web-1.0-SNAPSHOT.jar" >Download Jar file link</a>

### Run

```
>    java -jar app-web-1.0-SNAPSHOT.jar
```

### Check
- Access to http://localhost:8080
- 로컬 환경 H2 설치 및 실행 확인 
- 로컬 환경 redis 설치 확인

<br>

## 🖥️ 사용 기술 
- JAVA 11, Spring Boot
- 멀티 모듈 프로젝트
- Redis
- H2 / JPA
<br>

## 📚 사용 라이브러리
| index |라이브러리| 사용 목적                             |
|-------|--------------|-----------------------------------|
| 0     | Lombok   | VO, DTO, Entity 관련 작업 용이          |
| 1     | Jackson | Json 데이터를 용이하게 다루기 위해 사용          |
| 2     | Spring Open Feign | 외부 API (kakao, naver open api) 연동 |
| 3     | Spring Cloud Circuitbreaker Resilience4j |Feign client 사용 시, Fallback 처리 위해 사용|
| 4     | Spring Data Jpa |관계형 DB 컨트롤을 위해 사용|
| 5     | Spring Data Redis |Redis 컨트롤을 위해 사용|
| 6     | Embedded-redis |로컬에서 redis 서버 컨트롤|
| 7     | Spring boot Starter Test | 테스트 코드 작성|


<br>

## 📑 API 명세서
* #### 🔗  <a href="https://github.com/tmfrl1552/blog-search/wiki/%EB%B8%94%EB%A1%9C%EA%B7%B8-%EA%B2%80%EC%83%89-API" >블로그 검색 API 명세서</a>
* #### 🔗  <a href="https://github.com/tmfrl1552/blog-search/wiki/%EC%9D%B8%EA%B8%B0-%EA%B2%80%EC%83%89%EC%96%B4-%EC%A1%B0%ED%9A%8C-API" >인기 검색어 API 명세서</a>

<br>

## 📌 구현 기능
### 0) 멀티 모듈 프로젝트 구성
* 각 모듈 간 기능 분리 

### 1) 블로그 검색
* 카카오 블로그 검색 API 장애 발생 시, 네이버 블로그 검색 API 연동
  * 네이버 또한 장애 발생 시, Empty 응답
* 카카오 API 이외의 다른 검색 소스 연동을 위한 관심사 분리 
* 블로그 검색 시, 검색어 수집 비동기 처리  

### 2) 인기 검색어 조회
* 인기 검색어 조회 시, 응답 속도 및 DB 부하를 고려하여 Redis 사용
* Redis 장애를 대비하여, 5분 주기로 DB에 검색어 insert
  * 테스트를 위해 5분 주기로 잡았으나, 실제 서비스에서는 요청량을 고려하여 주기 설정 필요
* 추후 TODO
  * Redis 용량을 위한 데이터 초기화 고려 필요

