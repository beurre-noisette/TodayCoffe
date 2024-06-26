<h1>오늘 커피 모마심?</h1>

# 오커모?

## Prologue
바쁘다 바쁜 현대사회에서 빠질 수 없는게 있다면 커피일텐데요! 늘 아메리카노만 먹던 사람도 가끔은 다른게 땡길때가 있습니다.
줄 가득 서있는 점심시간 카페 키오스크앞에서 메뉴를 고르기도 눈치보이고 ..
나도 내가 뭐를 마시고 싶은지 모르겠는 날..!
간단하게 "딸깍" 몇번이면 제가 추천해 드릴게요 :)

### 오커모는 새싹개발자인 저와 함께 성장해나가는 프로젝트입니다.

## v0.5

### 업데이트 내용
1. 지난 v0.4에서 Swing으로 구현한 GUI에 다음과 같은 기능이 추가되었습니다.
2. 실행과 동시에 나타나는 화면에서 사용자는 '메뉴 추천받으러 가기'와 '메뉴확인 및 나만의 메뉴북 만들러 가기'를 선택할 수 있습니다.
3. '메뉴 추천받으러 가기'를 클릭하면 나타나는 `JFrame`에서 사용자는 `1. 컴포즈커피, 2. 빽다방, 3. 나만의 메뉴북` 중에서 메뉴를 추천 받을 수 있으며, 이 때 하단의 `JScroll`을 움직여 설정된 카페인값과 설탕량값 보다 큰 메뉴들을 추천 받을 수 있습니다.
  3-1. 카페인과 설탕량을 1회 스크롤하면 증가하는 값은 각각 `75(샷 하나 기준)`와 `10(한 펌프 기준)` 입니다.
  3-2. 카페인과 설탕량을 스크롤하면 각각 우측의 컵에 `검정색(카페인)`과 `하늘색(설탕량)`이 조금씩 차오르는 이미지를 넣어 사용자가 가시적으로 얼만큼 각 수치를 증가시켰는지 알 수 있게 했습니다.
4. '메뉴확인 및 나만의 메뉴북 만들러 가기'를 클릭하면 나타나는 `JFrame`에서 사용자는 각 카페의 아이콘(컴포즈커피, 빽다방)을 클릭하여 해당 카페의 전 메뉴를 확인할 수 있습니다.
  4-1. 표기되는 정보는 `메뉴이름, 가격, 칼로리, 카페인, 당류, 해당메뉴를 판매하는 가게`입니다.
  4-2. 메뉴가 표시된 상태인 `Table`의 행을 클릭한 채(중복 선택 불가, 각 셀의 값 변경 불가) 우측의 `+`버튼을 클릭하면 `나만의 메뉴북`에 해당 메뉴가 추가됩니다. (이 때 중복추가는 불가하게 설정하였습니다.)
  4-3. 우측의 즐겨찾기 버튼을 누르면 현재 추가된 메뉴들을 확인할 수 있으며, `Table`의 행을 클릭한 채(중복 선택 불가, 각 셀의 값 변경 불가) 우측의 휴지통 버튼을 클릭하면 해당 메뉴가 `나만의 메뉴북`에서 삭제됩니다.
5. 최초 화면에서 `엄지척 이미지`를 클릭하면 최근 유행하는 밈을 이스터에그로 만나볼 수 있게 설정해두었습니다.

### 느낀점
+ 프로그램을 만들면 이스터에그를 꼭 넣어보고 싶다고 생각했는데, 넣어볼 수 있어서 무척 즐거웠습니다. 프로그램의 스케일이 커지면 조금 더 개연성 있는 곳에 뜬금없는 이스터에그를 넣어보고 싶습니다.
+ 프로그램을 다 제작한 다음 `Jar`파일로 `export`하여 `exe`형식으로 변환한 다음 지인들에게 보여주었는데, `JRE`가 없는 환경에서는 실행할 수 없었던 경우와 `DataBase`가 없어서(본 프로그램은 제 로컬pc를 DB로 사용하고 있습니다.) 원하는 기능을 모두
  보여주지 못했던 점이 매우 아쉬웠습니다. 차후 더 발전시켜 웹에서 누구나 이용할 수 있게 해보고자 합니다.
+ 웹에서 누구나 이용할 수 있게 발전시킬 때 회원 기능을 도입하고자 합니다.
+ 프로그램을 짜는 입장에서 '이렇게 이용하겠지?' 하는 부분을 사용자는 그렇게 이용하지 않는 경우도 왕왕 있음을 느꼈습니다. 제작자의 입장뿐 아니라 사용자의 입장도 고려하여 만들어야 함을 다시 한번 느꼈습니다.

## v0.3 & v0.4

### 업데이트 내용
1. 더 이상 콘솔창에서만 활용하는 것이 아니라 프로그램으로써 커피추천기를 이용해볼 수 있게 SWING을 이용하여 GUI로 구성해보았습니다.
2. 사용자는 프로그램 실행과 함께 제가 작성해놓은 컴포즈커피와 빽다방의 메뉴를 `data` 폴더 생성 -> 데이터 삽입의 과정을 통해 로컬pc에 저장할 수 있습니다.
3. 이후 컴포즈커피와 빽다방 중 원하는 가게를 선택하면 새로운 JFrame이 등장하고, 여기서 사용자는 (1) 카페인이 있는 메뉴, (2) 카페인이 없는 메뉴, (3) 전체 메뉴 중 하나의 선택지를 고를 수 있습니다.
4. 선택과 동시에 `Random` 메서드를 이용해 난수를 생성하고 이를 `index`로 이용하여 `ArrayList<Drink> Compose or Paiks`의 리스트에서 정보를 `get`해올 수 있습니다.
5. 이렇게 `get`해온 정보는 `Drink` 생성자에 정보가 담기게되고 이를 `interface`를 이용하여 `CoffeeMain` 클래스와 소통합니다.
6. `CoffeMain`클래스는 인터페이스 구현을 통해 `Compose` 혹은 `Paiks`에서 생성된 `Drink` 객체를 전달받아 `textField.setText();`를 수행하게 됩니다.

### 느낀점
+ `Choice~~Coffee` 클래스에서 생성된 정보를 어떻게 `CoffeeMain`클래스에게 전달할 수 있을지를 무척이나 고민했고, 인터페이스를 구현하여 메서드를 이용해 정보를 주고받는 방식으로 구현해보았습니다. 이를 통해 각 객체가 유기적으로 데이터를 전달하는 방식을 알아볼 수 있었습니다.<br>
+ 커피추천에 필요한 `Drink`의 데이터를 이전 버전까지는 메서드를 통해 매번 메모리에 할당하였는데, `FileUtil` 클래스를 통해 로컬PC에 해당 정보를 저장하는 방식을 구현해보았습니다. 여기서 `data`폴더의 `File`이 잘 관리되지 않는다면(원하는 정보가 아닌 다른 정보가 있는 경우) 원하는대로 동작하지 않을 수 있음을 확인했고(카페인이 없는 메뉴를 추천받고자 했는데, 카페인이 있는 메뉴가 추천됨 등...) `InvalidClassException`을 마주하고 해결하는 방법에 대해 익힐 수 있는 계기가 되었습니다.

## v0.2

### 업데이트 내용

1. MVC(Model-View-Controller)패턴을 지향하여 클래스를 구성하였습니다. <br>
  1-1. Drink(Model), CoffeeDao(Controller, Interface), CoffeeDaoImpl(Controller), CoffeeMain(View)
2. 사용자는 `CoffeeMain` 클래스의 `console`에서 `v0.1`과 마찬가지로 선택에 따라(Caffeine, DeCaffeine, AllBeverage)하나의 정보를 얻을 수 있습니다.
3. `Controller`인 `CoffeeDaoImpl`에서 메뉴의 정보를 배열에 선언하고 초기화할 수 있는 메서드를 정의하였고, `CoffeeMain`클래스에서는 음료의 데이터에 접근할 수 없습니다.
4. 가게를 **메가커피**와 **컴포즈커피** 두 종류로 늘렸습니다.

#### 느낀점
+ OOP를 지향하여 프로그래밍하려고 노력해보았습니다, 아직 익숙하지 않은 저이기에 과정은 순탄치 않았으나 결과물을 보니 OOP를 지향한 프로그래밍이 갖는 이점을 체화해가는 것 같아 무척이나 좋은 시간이 되었습니다.

## v0.1

### 업데이트 내용

1. `CoffeeInfo` 클래스와 `CoffeeMain` 클래스로 구성하였습니다.
2. 사용자는 `CoffeeMain`클래스의 `console`을 통해 다음과 같은 동작을 할 수 있습니다. <br>
2-1. 카페인이 들어있는 메뉴 중 1개의 정보를 얻기 <br>
2-2. 카페인이 들어있지 않은 메뉴 중 1개의 정보를 얻기 <br>
2-3. 전체 메뉴 중 1개의 정보를 얻기 <br>
3. 2의 동작을 위해 `CoffeeMain`클래스의 메서드를 통해 **메가커피** 음료 10종에 대한 정보를 배열에 저장하고 `main`함수 내에서 불러올 수 있게 구성하였습니다.
