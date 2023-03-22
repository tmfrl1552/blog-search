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
  - 계정 정보 (Default) 
    - username : sa

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
> 각 모듈 간 기능 분리 및 의존성을 낮추기 위해 확장성을 고려한 멀티 모듈 프로젝트 구성
  * app-web - blog search 서비스의 로직 담당 모듈
  * core - 다른 서비스와 함께 사용 가능한 공통 모듈
  * clients - 외부 API 호출용 모듈
  * repository - DB 연동 모듈 

### 1) 블로그 검색
> 카카오 검색 API를 사용하여 키워드를 통해 블로그 검색 기능
* 카카오 API 이외의 다른 검색 소스 연동에 대한 확장성을 고려하여 관심사 분리
* 카카오 블로그 검색 API 장애 발생 시, 네이버 블로그 검색 API 연동
  * 네이버 또한 장애 발생 시, Empty 응답하여 클라이언트(app, web 등)에서는 정상 처리 가능
* 블로그 검색 시, Redis를 연동한 검색어 수집 비동기 처리

### 2) 인기 검색어 조회
> 사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드 노출
* 검색어 저장
  * 검색어 저장 시, 트래픽이 많을 경우 지속적인 DB 접근 및 정렬은 장애 발생 우려가 있으므로<br>성능 측면에서 더 나은 Redis Sorted Set을 사용하여 검색어 저장
    * Redis Sorted Set 선택 이유
      * 싱글 스레드이므로, 동시성을 보장하기에 유리
      * 정렬 상태를 유지함으로써, 응답 속도 향상
  * Redis Sorted Set의 동시성을 추가 보장하기 위해 MULTI/EXEC 명령 사용 
* 인기 검색어 조회
  * Redis 조회하여 인기 검색어 응답
    * Sorted Set 조회 시, 시간 복잡도 O(log(n))으로 데이터가 증가할수록 성능 저하의 우려가 있으나<br> Read와 Write의 두마리 토끼를 모두 잡을 수는 없다고 판단
* Redis 장애 대비
  * Redis 장애를 대비하여, 5분 주기로 DB에 검색어 insert
    * 테스트를 위해 5분 주기로 잡았으나, 실제 서비스에서는 요청량을 고려하여 주기 설정 필요
  * 인기 검색어 조회 시, redis 오류가 발생한다면 DB에서 인기 검색어 조회
* 추후 TODO
  * Redis 용량을 고려한 추가 방안 고민 필요
    * ex) 일정 주기 간격으로 redis batch성 초기화   



