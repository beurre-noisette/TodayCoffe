package version05.jdbc;

public interface Oraclejdbc {
    // Oracle Database에 접속하기 위한 라이브러리 정보와 서버 주소, 포트, SID 정보
    public static final String URL = "jdbc:oracle:thin:@DESKTOP-VLB5QQ3:1521:xe";

    // Oracle Database에 접속할 수 있는 계정 이름
    public static final String USER = "scott";

    // Oracle Database에 접속할 때 사용할 계정의 비밀번호
    public static final String PASSWORD = "tiger";
}

