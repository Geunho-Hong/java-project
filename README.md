Java-project
============

### 테스트 기반과 기능별로 메소드를 구현한 자바 Project

-----------------------------------------------------

### BaseBall Game

  1. 1부터 9까지 서로 다른 수로 이루어진 3자리의 수를 맞추는 게임

  2. 같은 수가 같은 자리에 있으면 스트라이크, 다른 자리에 있으면 볼, 같은 수가 전혀 없으면 포볼 또는 낫싱

  3. 컴퓨터가 상대방 역할 - 컴퓨터는 임의의 수 3개를 선택 게임 플레이어는 3개의 수를 입력 -> 입력한 숫자에 대한 결과 출력

  4. 3개의 숫자를 맞히면 게임이 종료

  5. 게임 종료 후 다시시작 or 프로그램 종료

  ex) (정답 : 425) 123 : 1스트라이크 456 : 1스트라이크 1볼 789 : 낫싱

---------------------------------------------------------

#### 기능 정리
  
  * User.java
  1. input() : 게임 플레이어로부터 자릿수 입력받기
  2. saveDigit() : 자릿수 저장하기
  3. isNumberCheck() : 자릿수 판별하기
  4. isNumberDifferent() : 서로 다른 숫자 입력시 True 리턴
  5. isNotZero() : 0값 입력 방지
  6. isValidateNumber() : 입력 검증
  
  * Computer.java
  
  1. compareNumber() : Strike , ball 체크후 출력하기
  2. isCheckNumber() : Strike,  ball 개수 추가하기
  3. isStrike() : Strike 판별여부
  4. isBall() : Ball 판별여부
  5. createNum() : 랜덤 값 생성하기
  
  * Game.java
  
  1. startGame() : 게임 생성하기
  
  * Main.java
  
  1. main() : game 호출하기
  2. reply() : 게임 재시작 or 종료
