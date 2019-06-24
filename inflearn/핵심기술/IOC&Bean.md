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

---

### 빈의 스코프

스코프

-   싱글톤 (Service, Repository등..)
-   프로토타입 (새로운 인스턴스가 그때마다 필요함 - `@Scope("prototype")`)
    -   Request
    -   Session
    -   WebSocket

프로토타입 빈이 싱근톤 빈을 참조하면?

-   아무 문제 없음

싱글톤 빈이 프로토 타입 빈을 참조하면?

-   클래스가 생성될 때 고정되므로 업데이트가 되지 않음
-   업데이트 하려면
    -   scoped-proxy
    -   Object-Provider
    -   Provider

`@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)`로 설정하는게
가장 POJO스럽다!

싱글톤 객체 사용시 주의할 점

-   프로퍼티가 공유.
-   ApplicationContext 초기 구동시 인스턴스 생성

---

### Environment 1부, Profile

ApplicationContext는 EnvironmentCapable을 상속 받았다.

-   EnvironmentCapable(interface) - getEnvironment(현재 적용된 Profile가져오기)

Profile(프로파일)

-   빈들의 그룹
-   Enviroment의 역할은 활성화할 프로파일 확인 및 설정

프로파일 유즈케이스

-   모니터링 용도의 빈은 Prod에서만 쓰고 싶을 때
-   테스트 환경에서 쓸 빈과 배포환경에서 쓸 빈이 다를 때

프로파일 정의하기

-   클래스에 정의하기
    -   @Configuration @Profile("test")
    -   @Configuration @Profile("prod")
-   메소드에 정의하기
    -   @Bean @Profile("test")

프로파일 정의하기

-   VMoption에 `-Dspring.profiles.active="test,A,B"`로 작성
-   @ActiveProfiles (테스트용)
-   IntelliJ에서 RunConfiguration에서 Active Profile정의

---

### Environment 2부, Properties

Properties(프로퍼티)

-   다양한 방법으로 정의할 수 있는 설정 값
-   Environment의 역할은 프로퍼티 소스 설정 및 프로퍼티 값 가져오기

프로퍼티에는 우선 순위가 있다.(계층화 되어 있음!)

-   StandardServletEnvironmen의 우선순위
    -   ServletConfig 매개변수
    -   ServletContext 매개변수
    -   JNDI (java:comp/env/)
    -   JVM 시스템 프로퍼티(`-Dkey="value"`)
    -   JVM 시스템 환경 변수(운영체제 환경변수)

@PropertySource

-   Environment를 통해 프로퍼티를 추가하는 방법

스프링 부트의 외부 설정 참고

-   기본 프로퍼티 소스 지원(application.properties)
-   프로파일까지 고려한 계층형 프로퍼티 우선 순위 제공

값가져오기

-   @Value("{app.key}") String key;(스프링 부트에서만!)
-   Environment.getProperties("app.name")

---

### MessageSource

국제화 (i18n) 기능을 제공하는 인터페이스.
ApplicationContext extends MessageSource

-   getMessage(String code, Object[] args, String, default, Locale, loc)
    스프링 부트를 사용한다면 별다른 설정 필요없이 messages.properties 사용할 수 있음
-   messages.properties
-   messages_ko_kr.properties
    릴로딩 기능이 있는 메시지 소스 사용하기
    ```code
    @Bean
    public MessageSource messageSource() {
    var messageSource = new ReloadableResourceBundleMessageSource(); messageSource.setBasename("classpath:/messages"); messageSource.setDefaultEncoding("UTF-8"); messageSource.setCacheSeconds(3); return messageSource;
    }
    ```

### ApplicationEventPublisher

이벤트 프로그래밍에 필요한 인터페이스 제공. 옵저버 패턴 구현체.
ApplicationContext extends ApplicationEventPublisher

-   publishEvent(ApplicationEvent event)
    이벤트 만들기
-   ApplicationEvent 상송
-   스프링 4.2 부터는 이 클래스를 상속받지 않아도 이벤트로 사용할 수 있다.
    이벤트 발생 시키는 방법
-   ApplicationEventPublisher.publishEvent();
    이벤트 처리하는 방법
-   ApplicationListener<이벤트> 구현한 클래스 만들어서 빈으로 등록하기.
-   스프링 4.2 부터는 @EventListener를 사용해서 빈의 메소드에 사용할 수 있다.
-   기본적으로는 synchronized.
-   순서를 정하고 싶다면 @Order와 함께 사용.
-   비동기적으로 실행하고 싶다면 @Async와 함께 사용.
    스프링이 제공하는 기본 이벤트
-   ContextRefreshedEvent: ApplicationContext를 초기화 했더나 리프래시 했을 때 발생.
-   ContextStartedEvent: ApplicationContext를 start()하여 라이프사이클 빈들이 시작 신호를 받은 시점에 발생.
-   ContextStoppedEvent: ApplicationContext를 stop()하여 라이프사이클 빈들이 정지 신호를 받은 시점에 발생.
-   ContextClosedEvent: ApplicationContext를 close()하여 싱글톤 빈 소멸되는 시점에 발생.
-   RequestHandledEvent: HTTP 요청을 처리했을 때 발생.

---

### IoC 컨테이너 9부: ResourceLoader

리소스를 읽어오는 기능을 제공하는 인터페이스
ApplicationContext extends ResourceLoader
리소스 읽어오기

-   파일 시스템에서 읽어오기
-   클래스패스에서 읽어오기
-   URL로 읽어오기
-   상대/절대 경로로 읽어오기
    Resource getResource(java.lang.String location)
