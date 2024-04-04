package version02;

public interface CoffeeDao {
    // field
    int DRINK_LENGTH = 10;

    // abstract method
    void showMegaCoffee(int index);

    void showComposeCoffee(int index);

    void setMegaDrinks();

    void setComposeDrinks();
}
