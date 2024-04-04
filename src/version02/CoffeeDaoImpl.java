package version02;

import version01.CoffeeInfo;

public class CoffeeDaoImpl implements CoffeeDao {
    // singleton start
    private static CoffeeDaoImpl instance = null;

    private CoffeeDaoImpl() {
    }

    public static CoffeeDaoImpl getInstance() {
        if (instance == null) {
            instance = new CoffeeDaoImpl();
        }

        return instance;
    }
    //-- singleton finish

    // array start
    private Drink[] megaDrinks = new Drink[DRINK_LENGTH];
    private Drink[] composeDrinks = new Drink[DRINK_LENGTH];
    //--- array finish

    // method start

    @Override
    public void setComposeDrinks() {
        composeDrinks[0] = new Drink("아메리카노", 20, 15, 156, 0);
        composeDrinks[1] = new Drink("카페라떼", 34, 155, 208, 10);
        composeDrinks[2] = new Drink("바닐라라떼", 30, 202, 208, 25);
        composeDrinks[3] = new Drink("카푸치노", 30, 191, 208, 13);
        composeDrinks[4] = new Drink("헤이즐넛라떼", 30, 203, 208, 25);
        composeDrinks[5] = new Drink("딸기스무디", 40, 510, 0, 104);
        composeDrinks[6] = new Drink("망고스무디", 40, 410, 0, 61);
        composeDrinks[7] = new Drink("블루베리요거트스무디", 40, 450, 0, 84);
        composeDrinks[8] = new Drink("딸기요거트스무디", 40, 437, 0, 82);
        composeDrinks[9] = new Drink("자몽에이드", 40, 175, 0, 42);
    }

    @Override
    public void setMegaDrinks() {
        megaDrinks[0] = new Drink("아메리카노", 2000, 15, 156, 0);
        megaDrinks[1] = new Drink("카페라떼", 3400, 155, 208, 10);
        megaDrinks[2] = new Drink("바닐라라떼", 3800, 202, 208, 25);
        megaDrinks[3] = new Drink("카푸치노", 3400, 191, 208, 13);
        megaDrinks[4] = new Drink("헤이즐넛라떼", 3800, 203, 208, 25);
        megaDrinks[5] = new Drink("딸기스무디", 4300, 510, 0, 104);
        megaDrinks[6] = new Drink("망고스무디", 4300, 410, 0, 61);
        megaDrinks[7] = new Drink("블루베리요거트스무디", 4500, 450, 0, 84);
        megaDrinks[8] = new Drink("딸기요거트스무디", 4500, 437, 0, 82);
        megaDrinks[9] = new Drink("자몽에이드", 4000, 175, 0, 42);
    }

    @Override
    public void showMegaCoffee(int index) {
        System.out.println("-----------------------------");
        System.out.println(megaDrinks[index].toString());
        System.out.println("-----------------------------");
    }

    @Override
    public void showComposeCoffee(int index) {
        System.out.println("-----------------------------");
        System.out.println(composeDrinks[index].toString());
        System.out.println("-----------------------------");
    }
}
