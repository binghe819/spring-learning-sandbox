# 스프링 놀이터 - DataBinding - Converter

<br>

* S 타입을 T 타입으로 변환할 수 있는 **매우 일반적인 변환기.**
    * Converter는 Formatter보다 더 제너럴(일반적인)변환기이다.
    * 웹 환경이 아닌경우에도 활용할 수 있다.
* 상태 정보 없음 == Stateless == 스레드세이프
* `ConverterRegistry`에 등록해서 사용

> 기본적인 Converter는 스프링에서 제공한다. 커스텀해야할 경우에만 위와 같이 사용하면 된다.
