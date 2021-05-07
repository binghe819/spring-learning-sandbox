# 스프링 놀이터 - 스프링 테스트 @DirtiesContext
> https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/annotation/DirtiesContext.html

<br>

## 핵심 내용
스프링에서 제공하는 `@SpringExtension`을 통한 테스트는 기본적으로 컨텍스트를 공유한다.

즉, 통합 테스트가 기본적인 설정이다. 물론 하나의 클래스를 단독으로 테스트하면 이상이 없다. 

하지만 모든 테스트 코드를 실행하게 되면 실패하는 테스트가 존재한다.

앞선 테스트에서 특정 Bean의 속성값을 바꾸거나, 제거하거나, 추가하게 되면 다음에 오는 테스트를 실패하게 만들 수도 있는 것이다.

이를 해결하는 방법은 `@DirtiesContext`다!

<br>

### @DirtiesContext란?
> It indicates the associated test or class modifies the ApplicationContext.
> 
> It tells the testing framework to close and recreate the context for later tests.

* 이 어노테이션을 통해 테스트를 수행하기 전, 수행한 이후, 그리고 테스트의 각 테스트 케이스마다 수행하기 전, 수행한 이후에 context를 다시 생성하도록 지시할 수 있다.

<br>

### 사용법
* 클래스 레벨
    * `BEFORE_CLASS`: 클래스 단위의 테스트가 시작하기 전에 컨텍스트 생성 - FreshContextTests
    * `BEFORE_EACH_TEST_METHOD`: 클래스의 모든 테스트 메서드마다 시작하기 전에 context 생성 - FreshContextTests
    * `AFTER_CLASS`: 클래스 단위의 테스트가 모두 끝난 다음 context 생성 (default) - ContextDirtyingTests
    * `AFTER_EACH_TEST_METHOD`: 클래스의 모든 테스트 메서드가 끝날 때 마다 context 재생성 - ContextDirtyingTests
* 메서드 레벨
    * `BEFORE_METHOD`: 특정 테스트 메서드 시작하기 전에 context 재생성 - FreshContextTests
    * `AFTER_METHOD`: 특정 테스트 메서드를 시작한 이후 context 재생성 (default) - ContextDirtyingTests

> 각 사용법의 테스트 코드는 test패키지 안에 있습니다!
