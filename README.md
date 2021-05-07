# 스프링 놀이터 - JUnit만으로 UserDao 테스트 (단위 테스트)

<br>

## 핵심 정리
> [코드](https://github.com/binghe819/spring-learning-sandbox/blob/test-only-junit/src/test/java/com/binghe/dao/UserDaoTest.java)

* JUnit을 통한 테스트는 매 테스트메서드마다 새로운 ApplicationContext를 픽스처를 통해서 생성해주었다.
* 만약 컨테이너에 등록해야 할 빈들이 많을 경우 매번 ApplicationContext를 생성하는 비용은 생각보다 높다.
