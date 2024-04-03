package main;

public class CoffeeInfo {
    // 업장 내 음료의 정보
    // 메뉴이름, 가격, 칼로리, 카페인 함유량, 당 함유량
    private String menuName;
    private int price;
    private int calorie;
    private int caffeine;
    private int sugar;

    public CoffeeInfo(String menuName, int price, int calorie, int caffeine, int sugar) {
        this.menuName = menuName;
        this.price = price;
        this.calorie = calorie;
        this.caffeine = caffeine;
        this.sugar = sugar;
    }

    // setter는 필요 없을 듯? 어차피 데이터는 내가 관리하는 거잖음

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String setMenuName) {
        menuName = setMenuName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int setPrice) {
        price = setPrice;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int setCalorie) {
        calorie = setCalorie;
    }

    public int getCaffeine() {
        return caffeine;
    }

    public void setCaffeine(int setCaffeine) {
        caffeine = setCaffeine;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int setSugar) {
        sugar = setSugar;
    }

    public String toString() {
        return ("메뉴이름 : " + menuName + "\n" + "가격 : " + price + "원" + "\n" + "칼로리 : " + calorie + "kcal" + "\n" + "카페인 함유량 : " + caffeine + "mg" + "\n" + "당 함유량 : " + sugar + "g" + "\n");
    }
}
