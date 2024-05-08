package version05.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import version05.model.Coffee;
import version05.view.RecommendRandomDrinks;

import static version05.model.Coffee.Entity.*;
import static version05.jdbc.Oraclejdbc.*;

import oracle.jdbc.OracleDriver;
import oracle.jdbc.driver.parser.Parseable;

public class CoffeeDao {

    // --------------- Singleton----------------
    private static CoffeeDao instance = null;

    private CoffeeDao() {
        try {
            // Oracle 드라이버를 등록
            DriverManager.registerDriver(new OracleDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static CoffeeDao getInstance() {
        if (instance == null) {
            instance = new CoffeeDao();
        }

        return instance;
    }
    // --------------- Singleton----------------

    // -------------- QUERY --------------------
    private static final String SQL_FIND_DRINKS = String
            .format("SELECT * FROM %s WHERE %s >= ? AND %s >= ? AND %s = ?", TBL_DRINKS, COL_CAFFEINE, COL_SUGAR, COL_CAFE_ID);

    private static final String SQL_FIND_FAVORITEDRINKS = String.format(
            "SELECT * FROM %s INNER JOIN %s ON %s.%s = %s.%s WHERE %s >= ? AND %s >= ?", TBL_FAVORITEDRINKS, TBL_DRINKS,
            TBL_FAVORITEDRINKS, COL_DRINK_ID, TBL_DRINKS, COL_DRINK_ID, COL_CAFFEINE, COL_SUGAR);

    private static final String SQL_READ_DRINKS = String.format("SELECT * FROM %s WHERE CAFE_ID = ?", TBL_DRINKS);

    private static final String SQL_DELETE_FAVORITEDRINKS = String.format("DELETE FROM %S WHERE %S = ?",
            TBL_FAVORITEDRINKS, COL_DRINK_ID);

    private static final String SQL_READ_FAVORITEDRINKS = "SELECT d.* FROM favoritedrinks f INNER JOIN drinks d ON f.drink_id = d.drink_id";

    private static final String SQL_INSERT_DATA_DRINKS_TO_FAVORITEDRINKS = String
            .format("INSERT INTO %s (%s) VALUES (?)", TBL_FAVORITEDRINKS, COL_DRINK_ID);

    private static final String SQL_CHECK_EXISTING_MENU = String.format("SELECT COUNT(*) AS count FROM %s WHERE %s = ?",
            TBL_FAVORITEDRINKS, COL_DRINK_ID);
    // -------------- QUERY --------------------

    // -------------- Method -------------------

    /**
     * ResultSet에서 각 컬럼의 값들을 읽어온 뒤 Coffee 타입 객체를 생성하고 리턴함
     *
     * @param rs
     * @return Coffee
     * @throws SQLException
     */
    public Coffee makeCoffeeFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt(COL_DRINK_ID);
        String name = rs.getString(COL_NAME);
        int price = rs.getInt(COL_PRICE);
        double calorie = rs.getDouble(COL_CALORIE);
        double caffeine = rs.getDouble(COL_CAFFEINE);
        double sugar = rs.getDouble(COL_SUGAR);
        String cafeId = rs.getString(COL_CAFE_ID);
        if (cafeId.equals("C")) {
            cafeId = "컴포즈커피";
        } else if (cafeId.equals("P")) {
            cafeId = "빽다방";
        }

        Coffee coffee = new Coffee(id, name, price, calorie, caffeine, sugar, cafeId);

        return coffee;
    }

    /**
     * DB와 관련한 리소스를 종료하는 메서드
     *
     * @param conn
     * @param stmt
     * @param rs
     */
    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * DB와 관련한 리소스를 종료하는 메서드
     *
     * @param conn
     * @param stmt
     */
    private void closeResources(Connection conn, Statement stmt) {
        closeResources(conn, stmt, null);
    }

    /**
     * DB 테이블 DRINKS에서 조건(사용자입력)에 맞는 행들을 리턴
     *
     * @return 조건에 맞는 테이블을 List<Coffee> 타입으로 반환
     */
    public List<Coffee> readWithOption(String caffeine, String sugar, String cafeId) {
        List<Coffee> result = new ArrayList<Coffee>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // DB 접속
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 실행할 Query를 갖고 있는 PreparedStatement 객체 생성
            stmt = conn.prepareStatement(SQL_FIND_DRINKS);
            stmt.setString(1, caffeine);
            stmt.setString(2, sugar);
            stmt.setString(3, cafeId);

            // Query를 DB로 전송해서 실행
            rs = stmt.executeQuery();

            // 결과 처리
            while (rs.next()) {
                Coffee coffee = makeCoffeeFromResultSet(rs);
                result.add(coffee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }

        return result;
    }

    /**
     * DB 테이블 FAVORITEDRINKS에서 조건(사용자입력)에 맞는 행들을 리턴
     *
     * @return 조건에 맞는 테이블을 List<Coffee> 타입으로 반환
     */
    public List<Coffee> readWithOption(String caffeine, String sugar) {
        List<Coffee> result = new ArrayList<Coffee>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // DB 접속
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 실행할 Query를 갖고 있는 PreparedStatement 객체 생성
            stmt = conn.prepareStatement(SQL_FIND_FAVORITEDRINKS);
            stmt.setString(1, caffeine);
            stmt.setString(2, sugar);

            // Query를 DB로 전송해서 실행
            rs = stmt.executeQuery();

            // 결과 처리
            while (rs.next()) {
                Coffee coffee = makeCoffeeFromResultSet(rs);
                result.add(coffee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }

        return result;
    }

    /**
     * DB 테이블 DRINKS에서 CAFE_ID에 알맞는 모든 정보를 불러옴
     *
     * @return 선택한 테이블(DRINKS)의 모든 정보를 List<Coffee>타입으로 반환
     */
    public List<Coffee> readAllFromDrinksTableWithCafeId(String cafeId) {
        List<Coffee> result = new ArrayList<Coffee>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = conn.prepareStatement(SQL_READ_DRINKS);
            stmt.setString(1, cafeId);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Coffee coffee = makeCoffeeFromResultSet(rs);
                result.add(coffee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }

        return result;
    }

    /**
     * DB 테이블 FAVORITESDRINK에서 모든 정보를 불러옴
     *
     * @return 선택한 테이블(FAVORITEDRINKS)의 모든 정보를 List<Coffee>타입으로 변환
     */
    public List<Coffee> readAllFromFavoritedrinksTable() {
        List<Coffee> result = new ArrayList<Coffee>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = conn.prepareStatement(SQL_READ_FAVORITEDRINKS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Coffee coffee = makeCoffeeFromResultSet(rs);
//				System.out.println(coffee);
                result.add(coffee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }

        return result;
    }

    /**
     * 유저가 선택한 행에 포함된 정보들을 CUSTOMIZEDDRINK로 INSERT하는 메서드
     */
    public void insertCustomizedTable(String name, int price, double calorie, double caffeine, double sugar,
                                      String cafeId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // DB 접속
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // PreparedStatement 객체 생성
            stmt = conn.prepareStatement(SQL_INSERT_DATA_DRINKS_TO_FAVORITEDRINKS);

            // 파라미터 설정
            stmt.setString(1, name);
            stmt.setInt(2, price);
            stmt.setDouble(3, calorie);
            stmt.setDouble(4, caffeine);
            stmt.setDouble(5, sugar);
            stmt.setString(6, cafeId);

            // 쿼리 실행
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 리소스 해제
            closeResources(conn, stmt);
        }
    }

    /**
     * 유저가 선택한 행에 포함된 정보들을 FAVORITEDRINKS테이블에서 DELETE하는 메서드
     */
    public void deleteFavoritedrinksTable(int drinkId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // DB 접속
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // PreparedStatement 객체 생성
            stmt = conn.prepareStatement(SQL_DELETE_FAVORITEDRINKS);

            // 파라미터 설정
            stmt.setInt(1, drinkId);

            // 쿼리 실행
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 리소스 해제
            closeResources(conn, stmt);
        }
    }

    public int insertFavoriteDrinkTable(int coffeeId) {
        int result = 0;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_INSERT_DATA_DRINKS_TO_FAVORITEDRINKS);
            stmt.setInt(1, coffeeId);
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }

        return result;
    }

    public boolean isMenuAlreadyExistInFavoritedrinks(int coffeeId) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(SQL_CHECK_EXISTING_MENU)) {
            pstmt.setInt(1, coffeeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // -------------- Method -------------------

}

