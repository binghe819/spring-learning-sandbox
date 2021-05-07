# 스프링 놀이터 - 스프링 테스트

<br>

## 놀이터
- [JUnit만으로 UserDao 테스트 (단위 테스트)](https://github.com/binghe819/spring-learning-sandbox/tree/test-only-junit)

<br>

## DB 설정 방법

<br>

### 외부에 따로 테스트 DB(H2)서버 사용
> 스프링 부트처럼 내장 DB를 지원하지 않을때 테스트용으로 사용하기 좋다.

<br>

#### H2 다운 및 실행
1. [홈페이지](https://www.h2database.com/html/download.html)에서 다운 가능합니다!
2. 다운 받은 압축파일을 풀고 `bin/h2.sh`를 실행시켜주면 h2 서버가 켜집니다!
* 실행시 허가가 안된다면 chmod를 통해서 권한을 주면 됩니다 :) `sudo chmod +x ./bin/*.sh`
* 정상적으로 실행된다면 h2 서버가 켜지고, h2-console이 웹페이지에 뜨게 됩니다.

<br>

#### TCP로 연결
* 최초 한번 `jdbc:h2:~/test`을 실행해줍니다
    * 해당 위치에 `~/test.mv.db`라는 파일이 생성됩니다. (굳이 확인 안해도 됩니다!)
* 이후부터는 `jdbc:h2:tcp://localhost/~/test`을 사용하면 TCP로 H2 DB에 연결 가능합니다!!

> test는 다른 이름으로 해도 됩니다 :)

<br>

#### 의존성 추가 및 DAO
* H2 의존성을 gradle로 추가해줍니다
* DataSource혹은 Connection을 Bean으로 등록하여 사용하면 된다.