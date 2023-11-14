미션 - 크리스마스 프로모션 🎄
=

# 🎯 진행 방식

### 게임 규칙

- 이벤트에는 여러 할인이 적용된다. 많은 할인을 제공해서 고객이 혜택을 체감할 수 있도록 한다.

### 게임 진행

- 12월 이벤트 기간 동안 고객으로 부터 방문예약을 받는다.
- 방문 날짜와 메뉴, 가격 등 다양한 요소들을 고려해서 할인을 제공한다.

### 할인 종류

- 크리스마스 디데이 할인을 제외한 할인은 12월 내내 진행된다.
- 🎁 크리스마스 디데이 할인
    - 1 ~ 25일 까지 진행되는 할인
    - 1000원부터 디데이가 하루 앞당겨질 때마다 100원씩 오른다.
    - ex) 25일 디데이 할인 = 3400원
- 🎁 평일 할인
    - 평일은 일 ~ 목 이다.
    - 디저트 메뉴를 하나 당 2023원 할인
- 🎁 주말 할인
    - 주말은 금, 토 이다.
    - 메인 메뉴를 하나 당 2023원 할인
- 🎁 특별 할인
    - 달력에 별 표시가 있으면 1000원 할인
- 🎁 증정 이벤트
    - <U>할인 전 총 주문 금액</U>이 12만원 이상일 경우 샴페인 하나 증정
- 혜택 받은 금액의 총합에 따라 다른 이벤트 배지를 제공한다.
    - 5천원 이상 = 별
    - 1만원 이상 = 트리
    - 2만원 이상 = 산타

### 주문 및 할인 관련 기준

- 총주문 금액 1먄원 이상부터 이벤트가 적용된다.
- 음료만 주문할 수 없다.
- 메뉴는 한 번에 최대 20개까지만 주문할 수 있다.

### 입력

- 예상 방문 날짜 입력
- 주문 할 메뉴와 개수를 입력

### 출력

- 입력 요구 문구
- 주문 결과
- 할인 전 총주문 금액
- 증정 메뉴
- 혜택 내역
- 총 혜택 금액
- 할인 후 예상 결제 금액
- 12월 이벤트 배지

### 요구사항

- 비즈니스 로직과 UI 로직을 분리한다.
- Enum을 활용한다.
- 데이터를 갖는 객체가 일을 하도록 한다.
- 프로그램 종료 시 `System.exit()`으로 강제 종료를 하지 않는다.
- 구현이 완료되면 `ApplicationTest`의 모든 테스트를 성공해야한다.
- 입력은 `Console` 라이브러리를 활용하여 받도록 한다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException` 발생 후 종료되어야 한다.
- indent depth는 2가 넘지 않아야 한다.
- 메서드가 한 가지 일만 하도록 작게 만든다.
- 테스트를 통해 기능 목록이 정상 작동 됨을 확인한다. 예외 상황도 테스트한다.
    - 도메인 로직에 단위테스트를 구현한다.

---

# 🤔 고민해 봐야 할 점

## ✅ 이벤트가 범용성을 가지려면 어떻게해야할까?

- 미션에는 2023년 12월로 달력의 형태가 정해져 있다.
- 그러나 이 이벤트를 다른 년도, 혹은 다른 월에 사용할 수도 있어야 범용성이 있는 프로그래밍이 아닐까?
- 그러므로 단순히 텍스트로 달력을 받아서 그대로 사용하기 보다는, 자바에서 제공하는 날짜, 시간등을 다루는 클래스를 사용하도록 하자.
- 여기에 지정한 년도와 월을 적용해서 자동적으로 이벤트 구성하고 진행할 수 있도록 한다.

## ✅ 달력에는 어느 클래스를 사용해야할까?

- 단순히 먼저 떠오른 것은 Calendar 클래스였다. (달력 = Calendar 니까..)
- 그러나, Calendar 나 Date 같은 클래스들은 스레드 세이프 하지 않은 문제점이 있다.
- 즉, 여러 스레드가 동시에 접근하다 보면 데이터 불일치나 예측 불가능한 문제가 발생할 수 있었다.
- 이러한 문제를 해결하기 위해 Java 8 에는 java.time 패키지가 추가되었고 이 API는 immutable하고 스레드 세이프하다.
- 그러므로, 날짜를 주로 다루며 연산해야 하므로 LocalDate 클래스를 사용하도록 한다.

## ✅ 날짜 간 계산은 어떻게 해야할까?

- 프로젝트에서 달력에서의 일 수 차이를 구하기위해 ChronoUnit을 사용했던 기억이 있다.
- 이 또한 java.time 패키지에 위치되어 있는 Enum 이다.
- 다양하고 강력한 날짜 계산 메서드를 제공하는데, 그중에서도 크리스마스 디데이 할인 시 활용할 수 있을 것 같아서 활용해보면 좋을 것 같다.
- ex) ```ChronoUnit.DAYS.between(startDate, endDate)```

## ✅ 월의 말일은 어떻게 구해야 할까?

- 범용성 있는 프로그래밍을 하기 위해서는, 월의 말일 조차 직접 기입할 수 없었다.
    - 실제로, 2월은 보통 28일까지이고, 30일까지만 있는 달도 있고 31까지 있는 달도 있기 때문이다.
- 이를 해결하기 위해, java.time에 위치되어 있는 YearMonth 라는 클래스를 활용하고자 한다.
    - 여기에 atEndofMonth를 통해서 해당 년월의 말일을 구한다.
    - 이를 통해, 윤달까지도 효과적인 말일 계산 가능하다!
  ``` java
  YearMonth yearMonth = YearMonth.of(year, month);
  LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
  ```

## ✅ 2023원 할인은 년도를 반영한게 아닐까?

- 평일 및 주말 할인을 보면 년도와 동일하게 2023원을 할인하고 있다.
- 그러므로, 이를 따로 정하기보다는, 년도에 맞게 할인 금액을 맞추도록 유추했다.

## ✅ 별 표시가 있는 할인은 빨간날에만 적용된게 아닐까?

- 달력을 보면 별 표시는 일요일 및 25일 크리스마스에만 있다.
- 이를 미루어 보았을 때, 특별 할인은 달력에서의 날짜가 빨간색일 때 적용되는 것이라고 유추했다.

## ✅ 전반적인 게임 로직은 어떻게 진행되는게 좋을까?

### 입력 정보 저장

- 먼저 입력을 받는 정보는 두 개다. (방문 일자, 주문 내역)
- 입력을 받고나서 두 정보를 컨트롤러에서 서비스 레이어로 보낸다.
- 서비스는 이 두 정보를 repository에 저장한다.

### 할인 정보 저장

- Service에서 이 정보들을 util의 혜택 정보 파악 메서드를 통해 혜택 정보 dto를 만든다.
- util에서 dto를 바탕으로 혜택 도메인을 채운뒤 최종적으로 service로 반환한다.
- 받은 혜택 정보 도메인을 repository에 저장한다.
- 추가적으로 혜택 금액 총합을 통해 어느 배지를 수령하게 될지 정보 또한 repository에 저장한다.

### 결과

- 저장된 값들을 바탕으로 최종적인 출력을 진행한다.
    - 기존 금액, 할인 정보, 최종 금액, 배지 등

---

# 🛠️ 기능 목록

## 💡 Main Class

### Application

- [ ] 게임 실행

<br>

## 💡 Domain

### VisitDate

- 방문 날짜를 담을 클래스
- [x] LocalDate date
    - LocalDate에 날짜를 담는다.
- [x] ```public int getDay();```
    - getDayOfMonth 메서드를 활용해서 일자를 반환한다.
- [x] ```public String getDayOfTheWeek();```
    - getDayOfWeek 메서드를 활용해서 요일을 반환한다.
- [x] 유효성 판단 메서드
    - 입력이 1~31 사이인지 판단

### Order

- 주문 내역을 담을 클래스
- [x] ```Map<Menu, Integer> details;```
    - 사용자의 입력 순서를 그대로 반영하기 위해 LinkedHashMap을 활용한다.
    - key에는 메뉴, value에는 개수를 담는다.
- [x] getter
- [x] ```public long getPriceSum()```
    - 할인 전 총주문 금액을 반환한다.
- [x] 유효성 판단 메서드
    - key에 들어오는 메뉴 이름이 존재하는 메뉴인지 판단
    - value에 들어오는 숫자가 0 이상인지 판단
    - 메뉴 총 개수가 20개 이하인지 판단
    - 중복해서 입력을 했는지 판단

### Benefit

- 할인 금액 및 증정 메뉴 가격을 담을 클래스
- [x] ```long dDayDiscount```
- [x] ```long weekdayDiscount```
- [x] ```long weekendDiscount```
- [x] ```long specialDiscount```
- [x] ```long giftPrice```
- [x] 증정 메뉴 반환 메서드
- [x] 디데이 할인 금액 반환 메서드
- [x] 평일/주말 할인 금액 반환 메서드
- [x] 특별 할인 금액 반환 메서드
- [x] 증정 이벤트 금액 반환 메서드
- [x] 총할인 금액 반환 메서드
- [x] 총혜택 금액 반환 메서드

### Badge

- 예약에 따라 어느 배지를 받는지 정보를 담을 클래스
- [x] ```String badgeName```
- [x] getter

<br>

## 💡 Dto

### BenefitInfo

- 방문 날짜와 주문 내역을 보고 할인 별 적용 여부를 담을 dto이다.
- [x] ```boolean isDDayOrBefore```
    - 1~25일인지 여부
- [x] ```boolean isWeekdayDiscount```
    - 평일 여부 -> false일 경우 주말
- [x] ```boolean isSpecialDiscount```
    - 달력에 별이 있는지 여부 (일요일 or 크리스마스)
- [x] ```boolean isQualifiedForGift```
    - 증정 메뉴(샴페인)를 받을 자격이 있는지 여부 (12만원 이상의 주문이 발생했는지 판단)

<br>

## 💡 Controller

### EventController

- [ ] run 메서드
    - 날짜 입력 받기
    - 주문 입력 받기
    - 주문 결과 및 혜택 보여주기
    - 배지 수령 정보 보여주기
- [ ] 주문 결과 도출 메서드
- [ ] 혜택 도출 메서드
- [ ] 배지 정보 도출 메서드

<br>

## 💡 Service

### EventService

- [ ] 방문할 날짜, 주문, 혜택 정보 저장 메서드
- [ ] 주문 결과 반환 메서드
- [ ] 혜택 반환 메서드
- [ ] 배지 수령 정보 반환 메서드

<br>

## 💡 Repository

### EventRepository

- [ ] 필드 : VisitDate, Order, Benefit, Badge 정보 저장
- [ ] save 메서드
- [ ] 주문메뉴 반환 메서드
- [ ] 할인 전 총주문 금액 반환 메서드
- [ ] 혜택 정보 반환 메서드
- [ ] 할인 후 예상 결제 금액 반환 메서드
- [ ] 12월 이벤트 배지 반환 메서드

<br>

## 💡constant (도메인에 활용할 상수)

### OrderConstant

- 주문 입력 시 활용할 Enum
- [x] EVENT_INIT_PRICE(10000)
- [x] ORDER_AMOUNT_LIMIT(20)

### GiftMenuConstant

- 증정 메뉴 Enum으로, 메뉴와 개수를 가진다.
- Menu menu, int count
- [x] GIFT(CHAMPAGNE, 1)

### BenefitConstant

- 혜택 관련 Enum
- [x] D_DAY(25)
    - 크리스마스 당일 -> d-day 이벤트 종료날짜
- [x] DISCOUNT_INIT_PRICE (1000)
    - d-day 시작 할인 금액
- [x] DAILY_DISCOUNT_INCREMENT(100)
    - 매일 100원씩 증가
- [x] STAR_DISCOUNT(1000)
    - 특별 할인
- [x] GIFT_QUALIFYING_PRICE(120000)
    - 증정 메뉴 기준
- [x] YEAR_BASED_DISCOUNT(이벤트 진행 년도)
    - EventTime에서 가져온 값으로 지정

### BadgeConstant

- String name, long qualifyingPrice
- [x] STAR_BADGE(”별”, 5000)
- [x] TREE_BADGE(”트리”, 10000)
- [x] SANTA_CLAUS_BADGE(”산타”, 20000)

<br>

## 💡constant (전역적으로 활용될 상수)

### EventTime

- 이벤트가 진행되는 시기를 설정할 Enum으로, 시작날짜, 종료 날짜 정보 또한 가진다.
- [x] EVENT_YEAR
- [x] EVENT_MONTH
- [x] EVENT_START_DAY
- [x] EVENT_END_DAY

### Category

- 카테고리 이름을 담을 Enum
- [x] Appetizer, Main, Dessert, Beverage

### Menu

- String category (Category Enum 활용하기)
- String name
- Long price
- [x] 전체 메뉴 상수처리
- [x] of 메서드
  - 메뉴이름을 기반으로 탐색하는 메서드 (+ 예외처리)

<br>

## 💡 View

### InputView

- 입력과 입력에 대한 출력을 담당하는 클래스이다.
- [ ] 예약일 요청을 출력하는 메서드
- [ ] 주문 요청을 출력하는 메서드
- [ ] 입력을 받아서 Map형태로 반환하는 메서드
    - 기본적으로 빈 문자열 체크 및 형식 체크만 하도록 한다.

### OutputView

- 출력을 담당하는 클래스이다.
- [ ] 미리보기 메세지 출력 메서드
- [ ] 주문 메뉴, 할인 전 총주문 금액, 증정 메뉴, 혜택 내역, 총혜택 금액, 할인 후 예상 결제 금액, 12월 이벤트 배지 출력메서드 (각각 구성)
- [ ] `OutputConstant`를 활용하여 `System.out.println()`을 통해 출력을 한다.

### InputConstant

- [x] DAY_REQUEST : `안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\\n12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)`
    - 12월의 경우 EventTime Enum에서 가져와서 상수에 넣기
- [x] ORDER_REQUEST : `주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)`
- [x] BONUS_REQUEST : `보너스 번호를 입력해 주세요.`
- [x] INPUT_MENU_DELIMITER : `,`
- [x] MENU_COUNT_SEPARATOR : `-`

### OutputConstant

- 출력에 필요한 상수들을 담은 Enum 클래스이다.
- [x] EVENT_PREVIEW_MSG : `에 우테코 식당에서 받을 이벤트 혜택 미리 보기!`
- [x] ORDERED_MENU_HEADER : `<주문 메뉴>`
- [x] ORDER_PRICE_SUM_HEADER : `<할인 전 총주문 금액>`
- [x] GIFT_MENU_HEADER : `<증정 메뉴>`
- [x] BENEFIT_HEADER : `<혜택 내역>`
- [x] D_DAY_DISCOUNT_MSG : `크리스마스 디데이 할인: `
- [x] WEEK_DAY_DISCOUNT_MSG : `평일 할인: `
- [x] WEEKEND_DISCOUNT_MSG : `주말 할인: `
- [x] SPECIAL_DISCOUNT_MSG : `특별 할인: `
- [x] GIFT_EVENT_MSG : `증정 이벤트: `
- [x] BENEFIT_PRICE_SUM_HEADER : `<총혜택 금액>`
- [x] AFTER_DISCOUNT_HEADER : `<할인 후 예상 결제 금액>`
- [x] EVENT_BADGE_HEADER : `<12월 이벤트 배지>`
    - 12월 EventTime에서 가져와서 적용
- [x] NONE : `없음`
- [x] PRICE_PATTERN : `#,###`
- [x] CURRENCY : `원`

<br>

## 💡 Utility

### DateUtil
- [x] 해당 년,월의 말일을 구하는 메서드

### EventUtil
- [ ] 방문 날짜와, 주문 내역을 기반으로 BenefitInfo를 생성 -> Benefit 받아서 리턴
- [ ] BenefitInfo를 기반으로 Benefit을 생성해서 반환하는 메서드

<br>

## 💡 Exception

### ErrorMessage

- 에러메세지들을 담을 Enum 클래스이다.
- [x] DAY_ERROR : [ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.
    - 날짜를 1~31 이외의 숫자를 입력했을 경우
- [x] ORDER_ERROR : [ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.
    - 없는 메뉴 입력, 개수(0 이하) 오류, 형식 오류, 중복 오류
- [x] SUM_PRICE_ERROR : [ERROR] 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.
    - 총 주문 금액이 1만을 넘지 않을 경우
- [x] BEVERAGE_ERROR : [ERROR] 음료만 주문하실 수 없습니다.
    - 음료만 주문했을 경우
- [x] ORDER_COUNT_ERROR : [ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.
    - 20개가 넘는 개수의 메뉴를 주문했을 경우

<br><br>

---

📠 TEST
=


