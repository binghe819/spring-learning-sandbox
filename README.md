# 스프링 놀이터 - MockMvc

<br>

## MockMvc란?
Spring은 Spring 3.2부터 `MockMvc`를 활용한 단위 테스트를 지원한다.

`MockMvc`는 기존의 `MockHttpServletRequest`, `MockHttpServletResponse`을 활용한 단위 테스트에서 발전되었다.

즉, 전체 Spring MVC 요청 처리를 수행하지만 진짜 서버대신에 Mock요청과 Mock응답을 통해 요청을 수행한다.

또한, Controller에 Service 객체를 Mock으로 주입하여 표현 계층에 대한 테스트에 집중할 수 있다.

> 참고. `spring-test` 의존성을 추가하면 `MockMvc`를 사용할 수 있다.

<br>

## SetUp
* `MockMvcBuilders.standaloneSetup(new HelloController()).build()`
    * 조금 더 단위 테스트에 가깝다. 매개변수로 주어진 Controller만 빈으로 등록하기 때문에.
* `MockMvcBuilders.webAppContextSetup(this.wac).build()`
    * `webAppContextSetup`는 실제 Spring MVC 구성을 로드하여 보다 통합에 가깝다.
    * 즉, 테스트하는데 더 많은 시간이 들 수도 있다. 필요없는 빈까지 등록하기 때문에.

<br>

## 번외 - @WebAppConfiguration
* ApplicationContext and WebApplicationContext both are almost same thing but there are some basic differences related to the web aware environment.
* In Spring ApplicationContext instances can be scoped. In the Web MVC framework, each DispatcherServlet has its own WebApplicationContext (i.e own *-servlet.xml), which inherits all the beans already defined in the root WebApplicationContext.
* Yoc can also override the inherited bean scope in the servlet-specific scope and also can define new scope-specific beans local to a given servlet instance.

<br>

```java
public interface WebApplicationContext extends ApplicationContext {
    ServletContext getServletContext();
}
```
* So we can say that both ApplicationContext and WebApplicationContext are the spring containers where WebApplicationContext is child of the ApplicationContext interface.
    * ApplicationContext (RootApplicationContext) -> IoC 컨테이너 (Service, Repository, POJO)
    * WebApplicationContext -> IoC 컨테이너 + MVC 관련 빈 (표현 계층 관련된 빈)

<br>

## 참고
* https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#spring-mvc-test-framework
* https://www.dineshonjava.com/difference-between-applicationcontext-webapplicationcontext-in-spring-mvc/
