# 우리 fisa 개발자 매칭 웹

## 🖥️ 프로젝트 소개
### 📌 프로젝트 제목 + 로고
아직 미정

### 📌 목적
- 우리 fisa 수강생들의 친목 도모를 할수 있게 하고자 합니다.
- 우리 fisa 내에서 해커톤과 같은 상황에서 원하는 개발 스택을 가진 개발자의 영입과 원활한 커뮤니케이션을 환경을 만들고자 합니다.
  
### 🕰️ 개발 기간
2024.01.05 ~ 2024.01.19

### 👤 팀원 소개
|***팀원***|역할|설명|
|----------|----|----|
|**김어진**|----|----|
|**염휘주**|----|----|
|**이명진**|----|----|
|**김수**|----|----|
|**박예린**|----|----|
|**김윤진**|----|----|

## 프로젝트 구조
### 📌 ERD
![infra (2)](https://github.com/fisaInfra/fisa_infraStudy/assets/115640392/4774a184-1a33-4c61-95b7-b2c24d7cfd01)

### ⚙️ 개발 환경
![image](https://github.com/MJLee39/fisa_infraStudy/assets/81970382/80ed99b4-f0b1-47dc-aa85-5c2e13a99c99)

#### [Language]
<div> 
<img src="https://img.shields.io/badge/jdk 17-437291?style=flat&logo=openjdk&logoColor=white"/> 
</div>

#### [IDE]
<div> 
<img src="https://img.shields.io/badge/eclipse-2C2255?style=flat&logo=eclipseide&logoColor=white"/> 
<img src="https://img.shields.io/badge/intelij-000000?style=flat&logo=intellijidea&logoColor=white"/> 
</div>

#### [Framework]
<div> 
<img src="https://img.shields.io/badge/spring-6DB33F?style=flat&logo=Spring&logoColor=white"/> 
<img src="https://img.shields.io/badge/springBoot-6DB33F?style=flat&logo=Spring boot&logoColor=white"/>
<img src="https://img.shields.io/badge/spring Security-6DB33F?style=flat&logo=Spring Security&logoColor=white"/>
</div>


#### [Database]
<div>
<p> 개발 데이터베이스 <img src="https://img.shields.io/badge/mysql-4479A1?style=flat&logo=mySql&logoColor=white"/></p>
<p> 운영 데이터베이스 <img src="https://img.shields.io/badge/oracle-F80000?style=flat&logo=oracle&logoColor=white"/></p>
</div>


#### [Test]
<div>
<p>성능 테스트 <img src="https://img.shields.io/badge/jmeter-D22128?style=flat&logo=apachejmeter&logoColor=white"/></p>
<p>레이어 별 테스트 <img src="https://img.shields.io/badge/junit5-25A162?style=flat&logo=junit5&logoColor=white"/></p>
<p>코드 커버리지 확인 라이브러리 <img src="https://img.shields.io/badge/jacoco-C10C0C?style=flat&logo=&logoColor=white"/></p>
<p>깃허브 내 소스 코드 커버리지 확인 사이트 <img src="https://img.shields.io/badge/coveralls-white?style=flat&logo=&logoColor=white"/></p>
</div>


## 계획
### 📌 목표
1. 설계와 관련된 작업을 모두 문서화하여 가시성을 확보하고자 합니다.
2. 테스트 커버리지 80%를 목표로 테스트 작업을 진행하고자 합니다.
3. 이슈나 에러가 난 상황을 트러블 슈팅을 통해 다음에 발생할 버그를 예방하고자 합니다.
4. 안정적인 운영과 개발을 위한 데이터베이스 환경 분리를 진행해 작업하고자 합니다.

### 💻 협업
1. 화면과 기능 작업을 위한 명세화 작업 진행
2. 깃허브를 이용한 협업과 커밋 메세지 컨벤션 사용
3. JIRA 일정 관리 툴을 이용한 협업 진행

## 기능 구현
### 📌 주요 기능
1. 프로필 조회
2. 로그인/회원가입 - 스프링 시큐리티 사용
3. 게시글 작성 및 댓글과 대댓글 작성
4. 마이페이지

### 📌 부가 기능
1. 회원과 관리자 계정 권한 분리
2. 팔로우 기능 - 차후에 진행

### 📌 고도화 계획
1. 클라우드 환경 도입
2. CI/CD 도입
