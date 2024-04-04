package version02;

import java.util.Random;
import java.util.Scanner;

public class CoffeeMain {

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private static CoffeeDao dao = CoffeeDaoImpl.getInstance();

    public static void main(String[] args) {
        CoffeeMain app = new CoffeeMain();
        ((CoffeeDaoImpl) dao).setMegaDrinks();
        ((CoffeeDaoImpl) dao).setComposeDrinks();

        boolean run = true;

        System.out.println("--- 오커모 v0.2 ---");
        while (run) {
            int menu = app.showProgram();

            switch (menu) {
                case 1:
                    app.showMegaCoffee();
                    break;
                case 2:
                    app.showComposeCoffee();
                    break;
                case 3:
                    run = false;
                    break;
                default:
                    System.out.println("숫자 1 | 2 | 3 중에서만 골라주세요 :(");
                    System.out.println("---------------------------------");
            }
        }

        System.out.println("--- 오늘도 화이팅 ---");
    }

    // method just only viewFunction
    private int showProgram() {
        System.out.println("원하시는 가게는 어디신가요?");
        System.out.println("[1] 메가커피 | [2] 컴포즈커피 | [3] 사실 안마실꺼지롱");

        int menu = Integer.parseInt(scanner.nextLine());

        return menu;
    }

    private void showMegaCoffee() {
        System.out.println("메가커피 메뉴 중 어떻게 추천해드릴까요?");
        System.out.println("[1] 카페인조아 | [2] 카페인안조아 | [3] 아무거나");
        int userChoice = Integer.parseInt(scanner.nextLine());

        if (userChoice == 1) {
            int randomIndex = random.nextInt(0, 5);
            dao.showMegaCoffee(randomIndex);
        } else if (userChoice == 2) {
            int randomIndex = random.nextInt(5, 10);
            dao.showMegaCoffee(randomIndex);
        } else if (userChoice == 3) {
            int randomIndex = random.nextInt(0, 10);
            dao.showMegaCoffee(randomIndex);
        } else {
            System.out.println("잘못 골랏슈...");
        }
    }

    private void showComposeCoffee() {
        System.out.println("컴포즈커피 메뉴 중 어떻게 추천해드릴까요?");
        System.out.println("[4] 카페인조아 | [5] 카페인안조아 | [6] 아무거나");
        int userChoice = Integer.parseInt(scanner.nextLine());

        if (userChoice == 4) {
            int randomIndex = random.nextInt(0, 5);
            dao.showComposeCoffee(randomIndex);
        } else if (userChoice == 5) {
            int randomIndex = random.nextInt(5, 10);
            dao.showComposeCoffee(randomIndex);
        } else if (userChoice == 6) {
            int randomIndex = random.nextInt(0, 10);
            dao.showComposeCoffee(randomIndex);
        } else {
            System.out.println("잘못 골랏슈...");
        }
    }
}
