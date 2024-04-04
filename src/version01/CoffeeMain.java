package version01;

import java.util.Random;
import java.util.Scanner;

public class CoffeeMain {

    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);
    private static final int MAX_LENGTH = 10;
    private CoffeeInfo[] coffeeInfos = new CoffeeInfo[MAX_LENGTH];

    public static void main(String[] args) {
        // (1) 내가 음료의 정보를 입력한 뒤
        // (2) 버튼 3개를 제시(카페인이 있는 것 5개, 카페인이 없는 것 5개, 전체 음료 10개
        // (3) 선택 된 버튼에 따라 제시할 음료의 범위가 정해지고(인덱스를 이용), 이때 Random 타입 활용
        // (4) 실행결과를 출력
        CoffeeMain app = new CoffeeMain(); // main()에서 method를 사용하기 위한 객체 생성
        app.initializeCoffeeInfos();

        boolean run = true;

        while (run) {
            int userInputData = app.showMainMenu();

            switch (userInputData) {
                case 1:
                    app.randomInCaffeine();
                    break;
                case 2:
                    app.randomInNonCaffeine();
                    break;
                case 3:
                    app.allBeverage();
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    System.out.println("1, 2, 3, 4 중에서 골라주세요 :(");
            }
        }
    }

    private void initializeCoffeeInfos() { // CoffeeMain 클래스에서 안보이게 할 수 있나?
        coffeeInfos[0] = new CoffeeInfo("아메리카노", 2000, 15, 156, 0);
        coffeeInfos[1] = new CoffeeInfo("카페라떼", 3400, 155, 208, 10);
        coffeeInfos[2] = new CoffeeInfo("바닐라라떼", 3800, 202, 208, 25);
        coffeeInfos[3] = new CoffeeInfo("카푸치노", 3400, 191, 208, 13);
        coffeeInfos[4] = new CoffeeInfo("헤이즐넛라떼", 3800, 203, 208, 25);
        coffeeInfos[5] = new CoffeeInfo("딸기스무디", 4300, 510, 0, 104);
        coffeeInfos[6] = new CoffeeInfo("망고스무디", 4300, 410, 0, 61);
        coffeeInfos[7] = new CoffeeInfo("블루베리요거트스무디", 4500, 450, 0, 84);
        coffeeInfos[8] = new CoffeeInfo("딸기요거트스무디", 4500, 437, 0, 82);
        coffeeInfos[9] = new CoffeeInfo("자몽에이드", 4000, 175, 0, 42);
    }

    private int showMainMenu() {
        System.out.println("=================================");
        System.out.println("당신을 위한 랜덤커피 추천기가 왔습니다.");
        System.out.println("오늘의 추천 브랜드는 '컴포즈커피'입니다.");
        System.out.println("아래의 메뉴중 하나를 골라주세요 :)");
        System.out.println("----------- 메뉴 -----------");
        System.out.println("[1] 카페인이 있어야 해.. [2] 카페인이 없었으면.. [3] 뭐든 좋아 :) [4] 그만할래");
        System.out.print("당신의 선택은? ");
        int userInputData = Integer.parseInt(scanner.nextLine());
        return userInputData;
    }

    private void randomInCaffeine() {
        int randomNumber = random.nextInt(0, 5);
        System.out.println(coffeeInfos[randomNumber].toString());
    }

    private void randomInNonCaffeine() {
        int randomNumber = random.nextInt(5, 10);
        System.out.println(coffeeInfos[randomNumber].toString());
    }

    private void allBeverage() {
        int randomNumber = random.nextInt(0, 10);
        System.out.println(coffeeInfos[randomNumber].toString());
    }

}
