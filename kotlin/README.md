# 코틀린

## 추가자료

1. [코틀린 온라인 에디터](https://try.kotlinlang.org/#/Examples/Hello,%20world!/Simplest%20version/Simplest%20version.kt)
2. [코틀린 학습 사이트](https://try.kotlinlang.org/#/Kotlin%20Koans/Introduction/Hello,%20world!/Task.kt)

## 코틀린을 쓰는 이유

1. 불필요한 코드 삭제

-   data class -> 롬북을 사용하여 어노테이션이 많았던 것을 데이터 클래스를 쓰면 더 줄여줌
-   if,when의 expression -> if나 when이 바로 return됨에 따라 중간 변수를 안 쓸수 있음
-   스트링 템플릿 -> "\$variable"처럼 짧게 가능

2. 안전한 코드

-   null관련 코드: Elvis연산자(?:), ? 체이닝, type? -> NPE를 빌드시에 잡을 수 있음
-   불변 키워드: val, 불변 컬렉션, open(클래스는 기본이 상속 안되게 설정) -> 혹시 모를 사이드 이펙트를 막는다
-   sealed class: 확장을 해야하는 상황에서 같은 파일 안에 있는 클래스만 되게 변경

3. 확장성

-   확장함수: 메소드를 추가하기 위해서 상속을 받지 않아도 되고 간단하게 함수를 추가할 수 있다. -> 불필요한 상속을 막는다.
-   확장 프로퍼티: 이하 동일
-   확장함수로 연산자까지 overriding이 가능

4. 본격적인 함수형 유틸리티 지원

-   수많은 이터러블 유틸리티 제공: find, map, reduce, filter등
-   {}, it, field로 간단한 프로그래밍 가능
-   클로저 손쉽게 가능

5. Spring등 기존 JAVA 코드와 섞어서 쓰는 것이 가능

-   스프링 사용가능(lateinit, lazy등)
-   원래의 도메인 그대로 사용가능(하지만 플랫폼 코드라 null에 대한 처리가 충분해야함)
