# 스프링 놀이터 - DataBinding - PropertyEditor

<br>

* PropertyEditor란?
    * 스프링 3.0 이전까지 DataBinder가 변환 작업하던 인터페이스.
* PropertyEditor는 여러 문제점이 있다.
    * Stateful 하기 때문에 스레드 안전하지 않다.
        * 싱글톤 빈으로 등록해서 사용하면 당연히 주소 공유로 인해 문제가 발생한다.
    * Object - String 간의 데이터 바인딩만 가능하다.
        * 더 범용적인 데이터 바인딩은 불가하다.

