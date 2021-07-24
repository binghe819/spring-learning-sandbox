# 스프링 놀이터 - Spring Validation
> 스프링부트없이 SpringCore를 실험하는 놀이터 - Validation

<br>

## Validation이란?
* 애플리케이션에서 사용하는 객체 검증용 인터페이스.
* **물론 Spring에선 대부분 MVC(DispatcherServlet)의 Presentation Layer에서 많이 사용되지만, 이 계층 전용 Validator는 아니다**
  * 어떠한 계층과도 상관없이 사용 가능하다. (Service, Persistence Layer에도 사용해도 상관없다.)
* [Bean Validation](https://beanvalidation.org/): 자바 표준 스펙 

<br>

## 특징
* 어떠한 계층과도 관계가 없다 => 모든 계층 (웹, 서비스, 데이터)에서 모두 사용해도 좋다.
* 구현체 중 하나로, JSR-303(Bean Validation 1.0)과 JSR-349(Bean Validation 1.1)을 지원한다.
* DataBinder에 들어가 바인딩 할 때 같이 사용되기도 한다.

<br>

## 스프링에서 제공하는 인터페이스
* boolean supports(Class clazz): 어떤 타입의 객체를 검증할 때 사용할 것인지 결정한다.
* void validate(Object obj, Error e): 실제 검증 로직을 이 안에석 ㅜ현한다.
  * 구현할 때 ValidationUtils를 사용하면 편리하다.
