# 토비의 스프링부트 - 이해와 원리 공부공간

## 스프링 부트 살펴보기


### 스프링 부트 소개 

스프링 부트는 스프링을 기반으로 실무 환경에 사용 가능한 수준의 독립실행형 애플리케이션을 복잡한 고민없이 빠르게 작성할 수 있게 도와주는 여러가지 도구의 모음이다.

### 스프링 부트의 핵심 목표
- 매우 빠르고 광범위한 영역의 스프링 개발 경험 제공
- 강한 주장을 가지고 즉시 적용 가능한 기술 조합을 제공하면서, 필요에 따라 원하는 방식으로 손쉽게 변형 가능
- 프로젝트에서 필요로 하는 다양한 비기능적인 기술(내장 서버, 보안, 메트릭, 상태체크, 외부 설정 방식 등) 제공
- 코드 생성이나 XML 설정을 필요로 하지 않음

### 컨테이너리스 개발 

`컨테이너 없는` 웹 애플리케이션 아키텍처란??

<img width="877" alt="image" src="https://user-images.githubusercontent.com/40031858/213370991-a0f27137-ac2f-45cb-a265-fa1ca10db304.png">

스프링 애플리케이션 개발에 요구되는 서블릿 컨테이너의 설치, WAR 폴더 구조, web.xm, WAR빌드, 컨테이너로 배치, 포트 설정,

클래스 로더, 로깅 등과 같은 필요하지만 애플리케이션 개발의 핵심이 아닌 단순 반복 작업을 제거해주는 개발도구와 아키텍처를 지원한다

설치된 컨테이너로 배포하지 않고 독립실행형(standalone) 자바 애플리케이션으로 동작

### Opinionated

스프링 부트는 `강한 주장을 가진 (opinionated)` 도구 이다.

스프링 버전, 스프링 생태계의 프레임워크, 표준 자바 기술, 오픈소스 라이브러리 등의 의존 관계를 확인하고 버전 호환성을 체크하는 작업은 고된 일익 성공적으로 잘 해내기 쉽지 않다

스프링 부트는 매 버전마다 사용할 기술의 종류를 선정하는 것만으로 사전 검증된 추천 기술과 라이브러리 구성, 의존 관계와 적용할 버전, 각 라이브러리의 세부

구성과 디폴트 설정을 제공한다. 스프링 부트를 사용한다면 스프링 부트가 추천하는 구성과 설정을 이용하려는 자세가 필요

하지만 원한다면 스프링 부트가 제시한 구성을 오버라이드 하거나 재구성하는 것이 가능한데, 매우 안전하고 명료한 방법을 통해서 원하는 방법으로 재구성할 수 있다. 

개발팀 또는 서비스의 특성에 맞게 스프링 부트 스타일의 도구를 만들어 적용할 수 있는 방법을 제공함.


----

## 프론트 컨트롤러

<img width="805" alt="image" src="https://user-images.githubusercontent.com/40031858/213623406-d104f688-3224-4833-838e-bf28afeaf143.png">

여러 요청을 처리하는데 반복적으로 등장하게 되는 공통 작업을 하나의 오브젝트에서 일괄적으로 처리하게 만드는 방식을 프론트 컨트롤러 패턴이라고 한다

https://martinfowler.com/eaaCatalog/frontController.html

서블릿을 프론트 컨트롤러로 만들려면 모든 요청, 혹은 일정 패턴을 가진 요청을 하나의 서블릿이 담당하도록 매핑해준다.

---

## 독립 실행형 스프링 애플리케이션

### 스프링 컨테이너 사용

<img width="357" alt="image" src="https://user-images.githubusercontent.com/40031858/213840623-27bb4d52-0a39-42c3-a535-13dfd289d32e.png">

스프링 컨테이너는 애플리케이션 로직이 담긴 평범한 자바 오브젝트, 일명 POJO와 구성정보(Configuration Metadata)를 런타임에 조합해서 동작하는 최종 애플리케이션을 만들어낸다

코드로 스프링 컨테이너를 만드는 가장 간단한 방법은 컨테이너를 대표하는 인터페이스인 ApplicationContext를 구현한 GenericApplicationContext를 이용하는 것이다.

이를 통해 컨테이너에 등록할 빈 오브젝트 클래스 정보를 직접 등록할 수 있다. 이를 참고해서 컨테이너가 빈 오브젝트를 직접생성한다

```kotlin
    val applicationContext = GenericApplicationContext().also {
        it.registerBean(HelloController::class.java, Supplier { HelloController() })
        it.refresh()
    }
```

컨테이너에 필요한 정보를 등록하고 refresh()를 이용해서 초기화 작업을 진행한다

ApplicationContext의 getBean() 메서드를 이용해 컨테이너가 관리하는 빈 오브젝트를 가져올 수 있다. 빈의 타입(클래스, 인터페이스) 정보를 이용해 해당 타입의 빈을 요청한다

---

## 자동 구성 기반 애플리케이션

### 메타 애노테이션과 합성 애노테이션


### `Meta-annotation`

```markdown
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component    // Meta Annotation
public @interface Service {
}
```

애노테이션에 적용한 애노테이션을 메타 애노테이션이라고 한다. 스프링은 코드에서 사용된 애노테이션의 메타 애노테이션의 효력을 적용해준다

@Service 애노테이션이 부여된 클래스는 @Service의 메타 애노테이션인 @Component가 직접 사용된 것처럼 컴포넌트 스캔의 대상이 된다

### `Composed-annotation`

```markdown
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller    // Meta Annotation
@ResponseBody  // Meta Annotation
public @interface RestController {
... }
```
합성 애노테이션은 하나 이상의 메타 애노테이션이 적용된 애노테이션을 말한다. 합성 애노테이션을 사용하면 모든 메타 애노테이션이 적용된 것과 동일한 효과를 갖는다.

