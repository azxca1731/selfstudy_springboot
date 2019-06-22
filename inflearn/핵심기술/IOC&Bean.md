### IOC and Bean

스프링 IoC컨테이너

-   BeanFactory
-   애플리케이션 컴포넌트의 중앙 저장소
-   빈 설정 소스로 부터 빈 정의를 읽어들이고, 빈을 구성하고 제공한다.

빈

-   스프링 IoC 컨테이너가 관리하는 객체
-   장점
    -   의존성 관리
    -   스코프
        -   싱글톤
        -   프로토타입
    -   라이프사이클 인터페이스(BeanPostProcessor - 빈으로 등록 되기 전 해야 할 일등)

ApplicationContext

-   BeanFactory
-   메시지 소스 처리 기능(다중언어, i18n)
-   이벤트 발행 기능
-   리소스 로딩 기능
-   [어플리케이션컨텍스트와 빈팩토리의 차이](http://wonwoo.ml/index.php/post/1571)

---

### Application Context

스프링 IoC 컨테이너의 역할

-   빈 인스턴스 생성
-   의존 관계 설정
-   빈 제공

Application Context 구현 방법

-   ClassPathXmlApplicationContext(XML에 빈 등을 명세함)
-   AnnotationConfigApplicationContext(JAVA - Annotation으로 관리, 컴포넌트 스캔과 연관)

빈 설정

-   빈 명세서
-   빈에 대한 정의를 담고 있다.
    -   이름
    -   클래스
    -   스코프
    -   생성자 아규먼트
    -   프로퍼트(Setter)

---

### @AutoWire

필요한 의존 객체의 "타입"에 해당하는 빈을 찾아 주입한다.

@AutoWired는 require = true가 기본 값 false로 해주면 못찾아도 구동 가능

사용 할 수 있는 위치

-   생성자
-   세터
-   필드

경우의 수

-   해당 타입의 빈이 없는 경우(X)
-   해당 타입의 빈이 한 개인 경우
-   해당 타입의 빈이 여러 개인 경우(X)
    -   빈 이름으로 시도(비추천)
        -   같은 이름의 빈 찾으면 해당 빈 사용
        -   같은 이름 못 찾으면 실패(X)

같은 타입의 빈이 여러개 일 때

-   @Primary(추천)
-   해당 타입의 빈 모두 주입 받기
-   @Qualifier (빈 이름으로 주입)

동작 원리

-   BeanPostProcessor를 구현한 AutowiredAnnotationBeanPostProcessor가 @AutoWired, @Value와 @Inject등을 처리한다.

---

### @Component와 컴포넌트 스캔

컴포넌트 스캔의 주요 기능

-   스캔 위치 설정
-   필터: 어떤 애노테이션을 스캔 할지 하지 않을지

@Component

-   @Repository
-   @Service
-   @Controller
-   @Configuration

동작 원리

-   @ComponentScan은 스캔할 패키지와 애노테이션에 대한 정보
-   실제 스캐닝은 ConfigurationClassPostProcessor라는 BeanFactoryPostProcessor에 의해 처리 됨
