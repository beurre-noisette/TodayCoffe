package version04;

import java.io.Serializable;

// 각 음료에 관한 데이터가 들어갈 Model
public class Drink implements Serializable {

    // field start
    private String name;
    private int price;
    private double calorie;
    private double caffeine;
    private double sugar;
    // --- field finish

    // constructor start
    public Drink(String name, int price, double calorie, double caffeine, double sugar) {
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

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public double getCaffeine() {
        return caffeine;
    }

    public void setCaffeine(int caffeine) {
        this.caffeine = caffeine;
    }

    public double getSugar() {
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