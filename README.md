# 스프링 테스트 - Spring Extension
> JUnit과 Spring Extension을 이용한 통합 테스트 놀이터

<br>

## 핵심 내용

> JUnit을 통한 테스트는 매 테스트메서드마다 새로운 ApplicationContext를 픽스처를 통해서 생성해주었다.
> 
> 만약 컨테이너에 등록해야 할 빈들이 많을 경우 매번 ApplicationContext를 생성하는 비용은 생각보다 높다.

😱 매 테스트마다 컨텍스트(컨테이너)를 생성해주는 것은 비용이 너무 높다. 이러한 문제를 해결하기 위해 스프링은 테스트를 따로 제공해준다.

* 테스트는 가능한 한 독립적으로 매번 새로운 객체를 만들어서 사용하는 것이 원칙이다.
* 하지만, 애플리케이션 컨텍스트(IoC 컨테이너)처럼 생성에 많은 시간과 자원이 소모되는 경우 테스트 전체가 공유하는 객체를 만들기도 한다.
이때도 테스트는 일관성있는 실행 결과를 보장해야 하고, 테스트의 실행 순서가 결과에 영향을 미치지 않아야 한다.
  
<br>

🤔 @ExtensionWith
* JUnit 프레임워크의 테스트 실행 방법을 확장할 때 사용하는 애노테이션이다.
* 테스트 컨텍스트를 지정해주면 JUnit이 테스트를 진행하는 중에 테스트가 사용할 컨텍스트를 만들고 관리하는 작업을 진행해준다.

🤔 @ContextConfiguration
* 자동으로 만들어줄 애플리케이션 컨텍스트의 설정파일 위치를 지정하는 것.

<br>

## 놀이터 실험 내용과 결과

<br>

### 모두 같은 컨텍스트 설정파일을 가진다면 컨텍스트를 공유하는가? 
> 결과는 Yes! 자세한 내용은 `UserDaoTest`와 `MultiContextUserDaoTest`를 참고하자.

😎 **여러 개의 테스트 클래스가 있는데 모두 같은 설정파일을 가진다면 스프링은 컨텍스트를 공유하게 해준다.**
* 따라서 수백 개의 테스트 클래스를 모두 단 한 개의 애플리케이션 컨텍스트만 만들어서 사용할 수 있다.
* **물론 테스트 클래스마다 다른 설정파일을 사용하도록 만들어도 된다.**

<br>

### ContextConfiguration 애노테이션 없이 사용 가능한가?
> 결과는 No! 더 자세한 내용은 `NonContextConfigurationUserDaoTest`를 참고하자.

* java.lang.IllegalStateException: Failed to load ApplicationContext 예외가 발생한다.
  * 즉, SpringExtension을 사용하기 위해선 IoC컨테이너의 ContextConfiguration이 꼭 필요하다!
  * 아마 스프링 부트는 자동적으로 설정해주지 않을까 싶다.
