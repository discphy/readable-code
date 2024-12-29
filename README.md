# Readable Code
읽기 좋은 코드를 작성하는 사고법 강의 코드 저장소

## 이름 짓기

+ 단수,복수형 사용하기  
+ 이름 줄이지 않기
+ 은어/방언 사용하지 않기
  + 새로운 사람이 팀에 합류했을 때 이 용어를 단번에 이해할 수 있게..  
+ 좋은 코드를 보고 습득하기

## 메서드 추상화 

+ 잘 쓰여진 메서드는 주제가 하나다.

## 메서드 선언부 

### 파라미터 
+ 파라미터의 타입, 개수, 순서를 통해 의미를 전달

## 반환타입 
+ 반환값이 있는경우 테스트 코드를 작성하는데 용이하다.

## 추상화 레벨 
+ 추상화 레벨을 동등하게 구성하라.

## Early return
+ Early return으로 else의 사용을 지양

> 메서드 리팩토링 시, 메서드 복제 방법을 이용하면 용이하다.

## 해피 케이스와 예외 처리 

### Null을 대하는 자세 

+ Optional 주의
  + `orElse(performanceHeavy())` : `performanceHeavy()`를 호출할 필요가 없는 경우에도 항상 실행 
  + `orElseGet(() -> performanceHeavy())` : `() -> performanceHeavy()` null인 경우에만 실행