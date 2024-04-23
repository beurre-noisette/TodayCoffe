package version04;

import java.io.Serializable;
import java.util.ArrayList;

import version04.Drink;
import static version04.FileUtil.*;

public class CoffeeDaoImpl implements CoffeeDao, Serializable {
    // singleton start
    private static CoffeeDaoImpl instance = null;

    private CoffeeDaoImpl() {
        initializeDataDir();

        composeDrinks = initializeData();
        paiksDrinks = initializeData();
    }

    public static CoffeeDaoImpl getInstance() {
        if (instance == null) {
            instance = new CoffeeDaoImpl();
        }

        return instance;
    }
    // -- singleton finish

    // field
    private ArrayList<Drink> composeDrinks;
    private ArrayList<Drink> paiksDrinks;
    // --- field

    // method start
    @Override
    public ArrayList<Drink> setComposeDrinks() {
        // caffeine index from 0
        composeDrinks.add(new Drink("에스프레소", 2000, 15, 156, 0));
        composeDrinks.add(new Drink("빅포즈 아메리카노", 3500, 30, 312, 0));
        composeDrinks.add(new Drink("아메리카노", 2000, 15, 156, 0));
        composeDrinks.add(new Drink("카페라떼", 3400, 155, 208, 10));
        composeDrinks.add(new Drink("카푸치노", 3400, 191.9, 208, 12.9));
        composeDrinks.add(new Drink("바닐라라떼", 3800, 202.3, 208, 25.));
        composeDrinks.add(new Drink("헤이즐넛라떼", 3800, 203.17, 208, 25.6));
        composeDrinks.add(new Drink("카라멜마끼아또", 3800, 284.7, 208, 35.4));
        composeDrinks.add(new Drink("카페모카", 4000, 332, 208, 30.3));
        composeDrinks.add(new Drink("돌체라떼", 4300, 269, 208, 30));
        composeDrinks.add(new Drink("흑당카페라떼", 4300, 297, 104, 42.4));
        composeDrinks.add(new Drink("아인슈페너라떼", 4700, 301.3, 208, 34.3));
        composeDrinks.add(new Drink("달고나라떼", 4500, 337.2, 156, 48.9));
        // --- caffeine index to 12

        // nonCaffeineLatte index from 13
        composeDrinks.add(new Drink("곡물라떼", 3800, 481.3, 0, 37.3));
        composeDrinks.add(new Drink("고구마라떼", 4000, 363, 0, 37));
        composeDrinks.add(new Drink("더블초코라떼", 4000, 399.2, 0, 34.6));
        composeDrinks.add(new Drink("쿠키초코라떼", 4000, 573.8, 0, 54.9));
        composeDrinks.add(new Drink("그린티라떼", 4000, 327.7, 0, 41.7));
        composeDrinks.add(new Drink("민트초코오레오라떼", 4000, 520, 0, 47.2));
        composeDrinks.add(new Drink("흑당밀크", 4000, 333, 0, 48.5));
        composeDrinks.add(new Drink("딸기라떼", 4300, 398.68, 0, 51.22));
        composeDrinks.add(new Drink("블루베리라떼", 4300, 539, 0, 75.6));
        composeDrinks.add(new Drink("망고라떼", 4300, 557, 0, 79.3));
        composeDrinks.add(new Drink("밀크티", 4300, 274, 0, 38.6));
        // --- nonCaffeineLatte index to 23

        // juice index from 24
        composeDrinks.add(new Drink("복숭아주스", 4300, 264, 0, 44));
        composeDrinks.add(new Drink("키위주스", 4300, 224, 0, 34));
        composeDrinks.add(new Drink("오렌지당근주스", 4300, 234, 0, 40));
        composeDrinks.add(new Drink("샤인파인케일주스", 4300, 234, 0, 50));
        // --- juice index to 27

        // smoothie index from 28
        composeDrinks.add(new Drink("딸기스무디", 4300, 510.4, 0, 103.55));
        composeDrinks.add(new Drink("망고스무디", 4300, 401.12, 0, 61.16));
        composeDrinks.add(new Drink("블루베리스무디", 4300, 314.09, 0, 54.13));
        composeDrinks.add(new Drink("유자스무디", 4300, 268, 0, 57.1));
        composeDrinks.add(new Drink("플레인요거트스무디", 4500, 445.83, 0, 81.5));
        composeDrinks.add(new Drink("블루베리요거트스무디", 4500, 449.8, 0, 84));
        composeDrinks.add(new Drink("딸기요거트스무디", 4500, 437.3, 0, 82.1));
        composeDrinks.add(new Drink("망고요거트스무디", 4500, 455.9, 0, 82.89));
        // --- smoothie index to 35

        // tea index from 36
        composeDrinks.add(new Drink("복숭아티", 3500, 199, 0, 47.5));
        composeDrinks.add(new Drink("자몽허니블랙티", 4000, 242.4, 0, 55.6));
        composeDrinks.add(new Drink("페퍼민트", 3000, 0, 0, 0));
        composeDrinks.add(new Drink("캐모마일", 3000, 0, 0, 0));
        composeDrinks.add(new Drink("로즈마리", 3000, 0, 0, 0));
        composeDrinks.add(new Drink("얼그레이", 3000, 0, 0, 0));
        composeDrinks.add(new Drink("블랙퍼스트", 3000, 0, 0, 0));
        composeDrinks.add(new Drink("자몽티", 4000, 239.8, 0, 49.1));
        composeDrinks.add(new Drink("레몬티", 4000, 242.2, 0, 50.3));
        composeDrinks.add(new Drink("유자티", 4000, 282.6, 0, 59.1));
        // tea index to 45

        // ade index from 46
        composeDrinks.add(new Drink("블루레몬 스페셜에이드", 4000, 161.6, 0, 39.6));
        composeDrinks.add(new Drink("청포도 스페셜에이드", 4000, 220.2, 0, 52.2));
        composeDrinks.add(new Drink("패션후르츠 스페셜에이드", 4000, 226, 0, 55.5));
        composeDrinks.add(new Drink("자몽에이드", 4000, 175.6, 0, 42.2));
        composeDrinks.add(new Drink("레몬에이드", 4000, 178.8, 0, 43.8));
        composeDrinks.add(new Drink("망고에이드", 4000, 181.9, 0, 39.4));
        composeDrinks.add(new Drink("유자에이드", 4000, 175.6, 0, 74.2));
        // ade index to 52

        writeDataToFile(composeDrinks);
        return composeDrinks;
    }

    @Override
    public ArrayList<Drink> setPaiksDrinks() {
        // caffeine index from 0
        paiksDrinks.add(new Drink("더블에스프레소", 1500, 22, 237, 0));
        paiksDrinks.add(new Drink("아메리카노", 2000, 13, 237, 0));
        paiksDrinks.add(new Drink("원조커피", 2500, 425, 371, 48));
        paiksDrinks.add(new Drink("원조커피 제로슈가", 2500, 327, 452, 0));
        paiksDrinks.add(new Drink("달달연유라떼", 3700, 394, 237, 54));
        paiksDrinks.add(new Drink("빽`s 카페라떼", 3000, 198, 237, 10));
        paiksDrinks.add(new Drink("블랙펄카페라떼", 4000, 429, 118.5, 59));
        paiksDrinks.add(new Drink("바닐라라떼", 3700, 433, 237, 38));
        paiksDrinks.add(new Drink("카페모카", 3500, 365, 237, 36));
        paiksDrinks.add(new Drink("카라멜마키아또", 3500, 382, 237, 43));
        paiksDrinks.add(new Drink("바나나 카페라떼", 4000, 303, 119, 39));
        paiksDrinks.add(new Drink("아이스크림카페라떼", 3700, 357, 237, 32));
        paiksDrinks.add(new Drink("아이스크림바닐라라떼", 4400, 520, 237, 49));
        paiksDrinks.add(new Drink("아이스크림카페모카", 4200, 512, 237, 50));
        paiksDrinks.add(new Drink("초코라떼", 3500, 387, 16, 56));
        paiksDrinks.add(new Drink("녹차라떼", 3800, 361, 152, 49));
        paiksDrinks.add(new Drink("민트초코라떼", 3500, 498, 38, 43));
        paiksDrinks.add(new Drink("토피넛라떼", 3500, 469, 51, 16));
        // caffeine index to 17

        // nonCaffeineLatte index from 18
        paiksDrinks.add(new Drink("고구마라떼", 3800, 413, 0, 56));
        paiksDrinks.add(new Drink("꿀밤라떼", 3500, 436, 0, 50));
        paiksDrinks.add(new Drink("바나나라떼", 3500, 295, 0, 42));
        paiksDrinks.add(new Drink("블랙펄라떼", 4000, 397, 0, 51));
        paiksDrinks.add(new Drink("딸기라떼", 3800, 331, 0, 54));
        // --- nonCaffeineLatte index to 22

        // juice index from 23
        paiksDrinks.add(new Drink("우리포도주스", 4500, 188, 0, 44));
        paiksDrinks.add(new Drink("제주감귤주스", 3800, 237, 0, 41));
        paiksDrinks.add(new Drink("완전블루베리주스", 4800, 231, 0, 49));
        paiksDrinks.add(new Drink("완전토마토주스", 4000, 159, 0, 36));
        paiksDrinks.add(new Drink("완전딸기주스", 4300, 224, 0, 43));
        paiksDrinks.add(new Drink("완전망고주스", 3500, 241, 0, 37));
        // --- juice index to 28

        // ade index from 29
        paiksDrinks.add(new Drink("딸기에이드", 4300, 184, 0, 39));
        paiksDrinks.add(new Drink("레몬에이드", 4500, 246, 0, 60));
        paiksDrinks.add(new Drink("청포도에이드", 4500, 234, 0, 56));
        paiksDrinks.add(new Drink("깔라만시에이드", 4300, 215, 0, 50));
        paiksDrinks.add(new Drink("유자에이드", 4500, 251, 0, 62));
        paiksDrinks.add(new Drink("자몽에이드", 4500, 258, 0, 61));
        paiksDrinks.add(new Drink("복숭아에이드", 4500, 241, 0, 54));
        paiksDrinks.add(new Drink("석류에이드", 3500, 141, 0, 32));
        // --- ade index to 36

        // smoothie index from 37
        paiksDrinks.add(new Drink("트로피칼팝핑스무디", 3800, 455, 0, 96));
        paiksDrinks.add(new Drink("패션후르츠스무디", 3800, 327, 0, 75));
        paiksDrinks.add(new Drink("망고패션스무디", 4000, 415, 0, 86));
        paiksDrinks.add(new Drink("고구마스무디", 481, 481, 0, 70));
        paiksDrinks.add(new Drink("블루베리요거트스무디", 5800, 484, 0, 74));
        paiksDrinks.add(new Drink("플레인요거트스무디", 4000, 494, 0, 94));
        paiksDrinks.add(new Drink("딸기요거트스무디", 4800, 448, 0, 45));
        // --- smoothie index to 43

        writeDataToFile(paiksDrinks);
        return paiksDrinks;
    }

    @Override
    public void showPaiksCoffee(int index) {
        System.out.println("-----------------------------");
        System.out.println(paiksDrinks.toString());
        System.out.println("-----------------------------");
    }

    @Override
    public void showComposeCoffee(int index) {
        System.out.println("-----------------------------");
        System.out.println(composeDrinks.toString());
        System.out.println("-----------------------------");
    }

    @Override
    public Drink readCompose(int index) {
        return composeDrinks.get(index);
    }

    @Override
    public Drink readPaiks(int index) {
        return paiksDrinks.get(index);
    }
}
