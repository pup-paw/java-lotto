# java-Lotto

### 개요

> 입력한 금액에 따라서 로또 번호를 자동으로 생성하고, 당첨 결과를 알려주는 프로그램

<details>
<summary>1단계 미션 - 포키와 작성한 기능 요구사항</summary>
<div markdown="1">

### 목표

##### 공통 목표

- TDD에 맞게 개발 진행해보기
- 원시값 포장해보기
- Enum의 장점을 진심으로 느껴보기
- ‘어떻게’ 보다 ‘무엇을’

##### 야호

- Collection API 활용해보기

##### 포키

- 페어와 이해 수준을 잘 싱크하기
    - stream 전파하기

---

## 기능 요구사항 목록

### 절차에 따른 목록

- [X]  구입 금액을 입력받는다
    - [X]  [예외] 구입 금액은 숫자여야 한다
    - [X]  [예외] 구입 금액은 0원보다 커야 한다
    - [X]  [예외] 구입 금액은 1000원 단위로 나뉜다

```markdown
구입금액을 입력해 주세요. 14000
```

- [X]  입력받은 금액을 로또의 금액(1000원)으로 나누어서 구매할 로또 수량을 구한다
- [X]  구매한 로또 수량을 출력한다

```markdown
14개를 구매했습니다.
```

- [X]  수량만큼 로또 번호를 생성한다
    - [X]  로또 한 장에 총 6개 숫자가 포함된다
    - [X]  각 번호는 1 ~ 45 사이의 랜덤값이다
    - [X]  로또 한 장 내에서 숫자는 오름차순으로 정렬된다
- [X]  생성 로또 번호를 출력한다

```markdown
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]
```

- [X]  지난 주 당첨 번호를 입력받는다
    - [X]  [예외] 숫자만 입력해야 한다
    - [X]  [예외] 모든 숫자가 1 이상 45 이하여야 한다
    - [X]  [예외] 숫자가 6개여야 한다
    - [X]  [예외] 중복된 숫자가 있을 수 없다

```markdown
지난 주 당첨 번호를 입력해 주세요. 1, 2, 3, 4, 5, 6
```

- [X]  보너스 번호를 입력받는다
    - [X]  [예외] 숫자만 입력해야 한다
    - [X]  [예외] 1 이상 45 이하여야 한다
    - [X]  [예외] 당첨 번호와 중복되면 안된다

```markdown
보너스 볼을 입력해 주세요. 7
```

- [X]  당첨 통계를 구해서 출력한다
    - [X]  각 숫자의 일치 여부 구하기
    - [X]  일치하는 숫자의 개수 구하기
    - [X]  일치 개수에 따른 등수 구하기
    - [X]  만약 5개가 일치한다면, 보너스 볼과 일치하는지 확인하기

```markdown
당첨 통계
---------
3개 일치 (5000원)- 1개 
4개 일치 (50000원)- 0개 
5개 일치 (1500000원)- 0개 
5개 일치, 보너스 볼 일치(30000000원) - 0개 
6개 일치 (2000000000원)- 0개
```

- [X]  총 당첨금을 구한다
    - [X]  각 등수별 당첨금을 구한다
- [X]  수익률을 구해서 출력한다
    - [X]  수익률 = 총 당첨금 / 구입 금액

```markdown
총 수익률은 0.35입니다.
```

### 도메인 설계

##### InputView

- 구매 금액 입력
- 지난주 당첨 번호, 보너스 번호 입력받기

##### ResultView

- 생성한 로또 출력
- 로또 수량 출력하기
- 당첨 통계 출력
- 수익률 출력하기

##### Money

- 구매 금액을 검사

##### Lotto

- 로또 수량 구하기
- 랜덤값 6개 부여

##### Lottos

- 수량만큼 로또 생성

##### WinningNumber

- 당첨 번호 검사

##### WinningNumbers

- 당첨 번호들과 로또 번호 비교하기
- 당첨 번호들의 개수와 중복 여부 검사

##### Prize

- 맞는 숫자 개수에 따른 당첨금

##### PrizeInformation

- 해당 등수의 당첨금 구하기

##### Controller

- 수익률 구하기

</div>
</details>

<details>
<summary>희봉의 첫번째 리뷰에 따른 수정사항</summary>
<div markdow="1">

1. Lotto 생성자에 대한 테스트 진행하기
    - [X] Lotto 객체 두개 생성 후 `isEqualTo()` 이용해 비교하기 -> `isNotEqualTo()` 이용
    - [X] `equals()`, `hashCode()` 재정의 -> 두 객체가 다른 참조값을 가져야하므로 일치할 필요 없음
    - [X] 테스트가 힘든 코드인 Collection.shuffle() 분리하기
2. `Arrays.asList()` 의 패키지
    - [X] `assertj.core` -> `java.util` 로 변경
3. `assert` 문이 없으면 테스트코드가 아니다
    - [X] 학습 테스트에서 출력을 이용해 값을 확인하지 말고 `assert` 문 사용하기
4. 배열보다는 리스트를 사용하기
    - [X] 배열이 사용된 곳이 있다면 리스트로 변경하기
5. LottoNumber 가 인터페이스로 구현된 목적을 확실히 하기
    - [X] 함수 재사용이 목적인지, 하나의 타입으로 묶기 위함인지 생각해보기
    - [X] 전자라면, 인터페이스가 아닌 조합을 사용하기
6. static을 사용하는 목적
    - [X] Controller 의 모든 메서드를 static 으로 만든 이유 생각해보기
7. 개인적 리팩터링
    - [X] 불변값에 final 을 사용해 불변임을 명시
    - [X] `NOMINEE_NUMBERS` -> `LOTTO_BALLS` 의미가 들어나는 이름 사용
    - [X] PrizeInformation 생성자 private 으로 변경
        - [X] 변경으로 인한 테스트 코드 수정
    - [X] Prize.getPrize() 변경으로 인한 PrizeTest 의 에러 수정

</div>
</details>

<details>
<summary>희봉의 두번째 리뷰에 따른 수정사항</summary>
<div markdow="1">

1. `map` 을 사용하지 않고 `WinningNumber` 자체가 중복인지 확인하기
   - [X] Number equals 테스트
   - [X] WinningNumber equals 테스트
   - [X] getDistinctCount() 에서 map 부분 지우고, 기존의 테스트 진행
   - [ ] 성공하면 getNumber() 삭제
2. 테스트에서 for문 사용하지 않고 정확한 값 넣어주기
   - [X] WinningNumbersTest 에서 @BeforeEach의 for문 삭제
3. 테스트마다 새로운 given 값이 필요하다면 각각 새로 만들기
   - [X] WinningNumbersTest 에서 테스트마다 새로운 given 값 작성
4. equals() 오버라이딩
   - [X] 인텔리제이의 자동완성 함수 내용으로 사용하기 (WinningNumber)
5. Number의 사용 범위
   - [X] Lotto 의 Integer 대신 Number 를 사용해도 될까?
6. Prize의 기본 값을 이용해 getPrize() 역할하기
   - [X] getPrizeByCount() 함수에서 필터로 prize.bonus 와 matchResult.isBonus() 값이 같은지 확인
</div>
</details>

<details>
<summary>2단계 미션 - 야호가 작성한 기능 요구사항</summary>
<div markdow="1">

### 절차에 따른 목록

1. 수동으로 구매할 로또 수 입력받기
   - [X] 숫자로 입력해야 함
   - [X] 총 구매한 로또 수 보다 작아야 함
   - [X] 0 이상이어야 함

```markdown
수동으로 구매할 로또 수를 입력해 주세요.
3
```

2. 수동으로 구매할 로또 번호 입력받기
   - [X] 수동 구매 로또 수 만큼 입력받아야 함
   - [X] `, ` 로 구분되어 입력돼야 함
   - [X] 중복되는 숫자가 있으면 안됨
   - [X] 각 숫자는 1 이상 45 이하의 숫자여야 함
   - [X] 6개의 숫자로 이루어져야 함

```markdown
수동으로 구매할 번호를 입력해 주세요.
8, 21, 23, 41, 42, 43
3, 5, 11, 16, 32, 38
7, 11, 16, 35, 36, 44
```

3. 수동 + 자동 생성된 로또 출력하기
   - [X] (수동 개수 + 자동 개수)가 구매한 개수와 같아야 함
   - [X] 수동과 자동 각각 몇개씩 구입했는지 출력해야 함

```markdown
수동으로 3장, 자동으로 11개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]
```

### 리팩터링 방향

1. 로또 숫자 하나하나를 LottoBall 로 취급
   - [X] LottoBall 은 1 이상 45 이하의 숫자
2. Lotto 를 AutoLotto 와 ManualLotto 로 나누기
   - [X] Lotto 는 LottoBall 6개로 이루어져야 함
   - [X] Lotto 의 LottoBall 은 중복될 수 없음
   - [X] AutoLotto 와 ManualLotto 개수의 합이 총 구매한 로또 수와 같아야 함렬
- [X] WinningBall 과 LottoBall 합쳐보기
- [X] 예외 발생시 에러 메세지 출력 후 재입력 요구


</div>
</details>

<details>
<summary>희봉의 세번째 리뷰에 따른 수정사항</summary>
<div markdow="1">

1. 같은 일급 컬렉션이 두개 존재
    - [X] `WinningBalls` 를 `Lotto winningBalls` 로 만들기
2. 테스트가 없는 메서드
    - [X] `Money` 의 `rate()` 에 대한 테스트 진행
    - [X] 그 외에 테스트를 안한 메서드는 없는지 확인
3. 너무 많은 역할을 하는 메서드
    - [X] `Lottos` 의 `purchase()` 메서드의 역할 분리
      1. 함수로 나누기
      2. 클래스로 나누기
    - [X] 테스트 진행
4. 배열보다는 리스트를 사용하자
    - [X] 배열을 사용 중인 곳이 있다면 리스트로 변경
5. 보너스 번호를 포함한 5등
    - [X] `Prize` 의 `getPrize()` 가 제대로 역할을 하는지 테스트 추가
    - [X] 테스트를 통과시키기 위한 리팩터링
6. 개인적 리팩터링
    - [X] 자동 로또와 수동 로또에 대한 생성자를 각각 정적 팩터리 메서드로 생성
    - [X] `PrizeInformations` 를 `EnumMap` 으로 만들어보기
    - [X] for 문을 최대한 Stream 을 이용해 작성해보기

</div>
</details>

<details>
<summary>희봉의 네번째 리뷰에 따른 수정사항</summary>
<div markdow="1">

1. money.rate() 에 나누어 떨어지지 않는 경우 테스트
   - [X] 소수점 3자리까지 올림으로 나타내기
   - [X] 테스트 진행
2. 불필요한 new ArrayList<>() 삭제
3. 의존성 낮추기
    - [X] match 를 여러 클래스에서 사용하지 않고 Lotto 내에 구현
4. 역할이 비슷한 객체 변경
    - [X] WinningBalls 와 BonusBall 합치기
5. getter 를 직접 사용하지 않기
    - [X] LottoBallDTO 만들기

</div>
</details>

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)