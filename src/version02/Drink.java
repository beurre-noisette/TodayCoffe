package version02;

// 각 음료에 관한 데이터가 들어갈 Model
public class Drink {

    // field start
    private String name;
    private int price;
    private int calorie;
    private int caffeine;
    private int sugar;
    // --- field finish

    // constructor start

    public Drink(String name, int price, int calorie, int caffeine, int sugar) {
        this.name = name;
        this.price = price;
        this.calorie = calorie;
        this.caffeine = caffeine;
        this.sugar = sugar;
    }

    //--- constructor finish

   // getter & setter start
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public int getCaffeine() {
        return caffeine;
    }

    public void setCaffeine(int caffeine) {
        this.caffeine = caffeine;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }
    //--- getter & setter finish

    // method start
    public String toString() {
        return ("메뉴이름 : " + name + "\n" + "가격 : " + price + "원" + "\n" + "칼로리 : " + calorie + "kcal" + "\n" + "카페인 함유량 : " + caffeine + "mg" + "\n" + "당 함유량 : " + sugar + "g" + "\n");
    }
    //--- method finish
}