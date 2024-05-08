package version05.model;

public class Coffee {
    // DB 테이블들의 컬럼 이름들을 상수로 선언
    public static final class Entity {
        public static final String COL_DRINK_ID = "DRINK_ID";
        public static final String COL_NAME = "NAME";
        public static final String COL_PRICE = "PRICE";
        public static final String COL_CALORIE = "CALORIE";
        public static final String COL_CAFFEINE = "CAFFEINE";
        public static final String COL_SUGAR = "SUGAR";
        public static final String COL_CAFE_ID = "CAFE_ID";

        public static final String TBL_DRINKS = "DRINKS";
        public static final String TBL_CAFE = "CAFE";
        public static final String TBL_FAVORITEDRINKS = "FAVORITEDRINKS";
    }

    // ----------- Field ------------------
    private int id;
    private String name;
    private int price;
    private double calorie;
    private double caffeine;
    private double sugar;
    private String cafeId;
    // ----------- Field ------------------

    // ------------ Cunstructor -----------
    public Coffee() {
    }

    public Coffee(int id, String name, int price, double calorie, double caffeine, double sugar, String cafeId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.calorie = calorie;
        this.caffeine = caffeine;
        this.sugar = sugar;
        this.cafeId = cafeId;
    }
    // ------------ Cunstructor -----------

    // ------------ Getter & Setter -----------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public double getCaffeine() {
        return caffeine;
    }

    public void setCaffeine(double caffeine) {
        this.caffeine = caffeine;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public String getCafeId() {
        return cafeId;
    }
    // ------------ Getter & Setter -----------

    @Override
    public String toString() {
        return "Coffee [id=" + id + ", name=" + name + ", price=" + price + ", calorie=" + calorie + ", caffeine="
                + caffeine + ", sugar=" + sugar + ", cafeId=" + cafeId + "]";
    }

}
