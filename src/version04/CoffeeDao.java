package version04;

import java.util.ArrayList;

import version04.Drink;

public interface CoffeeDao {
    // abstract method
    void showPaiksCoffee(int index);

    void showComposeCoffee(int index);

    /**
     * CoffeeDaoImpl의 List에 빽다방 음료데이터 입력
     *
     * @return setPaiksDrinks리스트
     */
    ArrayList<Drink> setPaiksDrinks();

    /**
     * CoffeeDaoImpl의 List에 컴포즈커피 음료데이터 입력
     *
     * @return setComposeDrinks리스트
     */
    ArrayList<Drink> setComposeDrinks();

    Drink readCompose(int index);

    Drink readPaiks(int index);

}
