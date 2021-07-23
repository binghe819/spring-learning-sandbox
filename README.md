# 스프링 놀이터 - SpringMVC (Boot 없이) - 인터셉터
> 스프링부트없이 SpringMVC를 실험하는 놀이터

<br>

## 인터셉터 설정 위치
```java
// preHandle 1
// preHandle 2
// 요청 처리 (HandlerAdapter)
// postHandle 2
// postHandle 1
// 뷰 랜더링
// afterCompletion 2
// afterCompletion 1
```

설정 위치마다의 자세한 내용은 아래와 같다.
* ```java
  boolean preHandle(request, response, handler)
  ```
  * **핸들러 실행하기 전에 호출**
  * "핸들러"에 대한 정보를 사용할 수 있기 때문에 서블릿 필터에 비해 보다 세밀한 로직을 구현할 수 있다.
  * **리턴값으로 계속 다음 인터셉터 또는 핸들러로 요청, 응답을 전달할지(true) 응답 처리가 이곳에서 끝났는지 (false) 알린다.**
    * true가 반환되면 핸들러 어댑터에 요청도 하지 않고 응답을 보내게 된다.
* ```java
  void postHandle(request, response, modelAndView)
  ```
  * **핸들러 실행이 끝나고 아직 뷰를 랜더링 하기 이전에 호출.**
  * "뷰"에 전달할 추가적이거나 여러 핸들러에 공통적인 모델 정보를 담는데 사용할 수도 있다.
    * **`modelAndView` 를 커스텀마이징할 수 있다.**
      * 모델에 새로운 정보를 추가 혹은 뷰를 변경.
  * 이 메서드는 인터셉터 역순으로 호출된다.
  * 비동기적인 요청 처리 시에는 호출되지 않는다.
* ```java
  void afterCompletion(request, response, handler, ex)
  ```
  * **요청 처리가 완전히 끝난 뒤 (뷰 랜더링 끝난 뒤)에 호출.**
  * preHandler에서 true를 리턴한 경우에만 호출된다.
  * 이 메서드는 인터셉터 역순으로 호출된다.
  * 비동기적인 요청 처리 시에는 호출되지 않는다.
