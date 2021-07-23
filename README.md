# 스프링 놀이터 - SpringMVC (Boot 없이) - @EnableWebMvc, WebMvcConfigurer
> SpringMVC에 대한 설정을 쉽게 도와주는 방법에 대한 놀이터 (다시 한번 강조하지만 스프링 부트아님!)

<br>

## @EnableWebMvc
> `@Enable`로 시작하는 애노테이션은 자바 설정에서 편의를 제공하기 위해 도입되었다고 한다.
>
> `@Enable`애노테이션은 개발자를 대신해서 많은 설정을 대신해준다.
>
> **이 방법이 스프링 부트없이 스프링을 설정하고 사용하는 기본적인 방법이다.**
>
> 가능하면 디버깅을 통해 직접 눈으로 확인해보자

```java
@Configuration
@EnableWebMvc
public class WebMvcConfig {
  ...
}
```

**🤔  `@EnableWebMvc` 란**

* `@Configure`에 `@EnableWebMvc` 애노테이션을 추가해주면 **설정이 완료된 여러 스프링 빈을 추가 및 수정해준다.**
    * **여러 빈을 추가하고 수정해주는 코드는 `DelegatingWebConfiguration`에 존재한다.**
* **또한, `DelegatingWebMvcConfiguration`을 통해 스프링 MVC(Web관련) 빈들을 쉽게 설정할 수 있게 도와준다.**

<br>

## DelegatingWebMvcConfiguration
* `DelegatingWebMvcConfiguration`은 `WebMvcConfigurationSupport`를 상속받는다.
    * **기본적인 Web 관련 기본 빈을 등록하고 설정하는 메서드는 모두 `WebMvcConfigurationSupport`에 존재한다.(상위 클래스 - 부가 로직)**
        * **MVC 자바 설정에서 기본 설정을 제공하는 메인 클래스이다.**
        * 대표적인 기본 설정은 Jackson, GSON... 추가 등이 있다.
    * **`DelegatingWebMvcConfiguration`는 `WebMvcConfigurer` 타입의 빈들을 통해서 등록되는 빈들을 커스터마이징 할 수 있게 해주는 역할만을 수행한다. (하위 클래스 - 핵심 로직)**
* **`setConfigurers`메서드에서 `WebMvcConfigurer` 타입의 빈들을 모두 주입받아서 `WebMvcConfigurerComposite` 타입의 객체에 주입하고 있다.**
    * **`WebMvcConfigurerComposite`에 저장된 `WebMvcConfigurer`타입의 빈들은 모두 Web(MVC)관련 빈들을 초기화할 때 사용된다.**

<br>

## WebMvcConfigurer

```java
public interface WebMvcConfigurer {
  ...  
  default void addInterceptors(InterceptorRegistry registry){}
  ...
  default void addFormatters(FormatterRegistry registry){}
  ...
  default void configureViewResolvers(ViewResolverRegistry registry){}
  ...
  default void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){}
  ...
}
```

* Spring MVC를 자바 기반으로 설정할 때 사용하는 인터페이스
    * 위임 형식으로 동작하기 때문에 리턴값이 없으며, 그저 `registry`에 설정만 해주고 스프링 컨테이너에게 설정을 위임한다.
* **`@EnableWebMvc`가 제공하는 빈을 커스터마이징할 수 있는 기능을 제공하는 인터페이스**
