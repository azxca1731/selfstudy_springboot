### 데이터 바인딩 추상화: PropertyEditor

org.springframework.validation.DataBinder<br />
기술적인 관점: 프로퍼티 값을 타겟 객체에 설정하는 기능<br />
사용자 관점: 사용자 입력값을 애플리케이션 도메인 모델에 동적으로 변환해 넣어주는 기능.<br />
해석하자면: 입력값은 대부분 “문자열”인데, 그
값을 객체가 가지고 있는 int, long, Boolean, Date 등 심지어 Event, Book 같은 도메인 타입으로도 변환해서 넣어주는 기능.

PropertyEditor

-   스프링 3.0 이전까지 DataBinder가 변환 작업 사용하던 인터페이스
-   쓰레드-세이프 하지 않음 (상태 정보 저장 하고 있음, 따라서 싱글톤 빈으로 등록해서 쓰다가는...)
-   Object와 String 간의 변환만 할 수 있어, 사용 범위가 제한적 임. (그래도 그런 경우가 대부분이기 때문에 잘 사용해 왔음. 조심해서..)

```java
public class EventPropertyEditor extends PropertyEditorSupport {
    @Override
    public String getAsText() {
        return ((Event)getValue()).getTitle();
    }
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        int id = Integer.parseInt(text); Event event = new Event(); event.setId(id);    setValue(event);
    }
}
```

---

### 데이터 바인딩 추상화: Converter와

Formatter
Converter

-   S 타입을 T 타입으로 변환할 수 있는 매우 일반적인 변환기.
-   상태 정보 없음 == Stateless == 쓰레드세이프
-   ConverterRegistry에 등록해서 사용

```java
public class StringToEventConverter implements Converter<String, Event> {
    @Override
    public Event convert(String source) {
        Event event = new Event(); event.setId(Integer.parseInt(source));
        return event;
    }
}
```

Formatter

-   PropertyEditor 대체제
-   Object와 String 간의 변환을 담당한다.
-   문자열을 Locale에 따라 다국화하는 기능도 제공한다. (optional)
-   FormatterRegistry에 등록해서 사용

```java
public class EventFormatter implements Formatter<Event> {
    @Override
    public Event parse(String text, Locale locale) throws ParseException {
        Event event = new Event(); int id = Integer.parseInt(text); event.setId(id);
        return event;
    }
    @Override
    public String print(Event object, Locale locale) {
        return object.getId().toString();
    }
}
```

ConversionService

-   실제 변환 작업은 이 인터페이스를 통해서 쓰레드-세이프하게 사용할 수 있음.
-   스프링 스프링 MVC, 빈 (value) 설정, SpEL에서 사용한다.
-   DefaultFormattingConversionService
-   FormatterRegistry
-   ConversionService
-   여러 기본 컴버터와 포매터 등록 해 줌.
    스프링 부트
-   웹 애플리케이션인 경우에 DefaultFormattingConversionSerivce를 상속하여 만든 WebConversionService를 빈으로 등록해 준다.
-   Formatter와 Converter 빈을 찾아 자동으로 등록해 준다.
