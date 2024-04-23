package version04;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import version04.Drink;

public class FileUtil {
    // field
    public static final String DATA_DIR = "data"; // 데이터 파일을 저장할 폴더 이름
    public static final String DATA_FILE = "drinks.dat"; // 데이터 파일 이름
    //--- field

    // constructor
    private FileUtil() {

    }
    //--- constructor

    // method

    /**
     * 매장별 메뉴 파일을 저장하는 폴더가 생성되어 있지 않다면 폴더를 새로 생성한다. 폴더를 새로 생성하고 File 타입 객체를 리턴 데이터 폴더가 이미 존재한다면, 그 폴더의 File 객체를 리턴한다.
     *
     * @return FileObject
     */
    public static File initializeDataDir() {
        boolean result = false;
        File file = new File(DATA_DIR);
        if (file.exists()) {
            System.out.println("데이터 폴더가 존재합니다.");
        } else {
            result = file.mkdir();
            if (result) {
                System.out.println("데이터 폴더를 생성합니다.");
            } else {
                System.out.println("데이터 폴더 생성에 실패했습니다.");
            }
        }
        return file;
    }

    /**
     * 음료정보 리스트가 저장된 데이터 파일을 읽고, 그 결과를 List<Drink>로 리턴
     *
     * @return List<Drink>
     */
    public static ArrayList<Drink> readDataFromFile() {
        ArrayList<Drink> list = null;

        File file = new File(DATA_DIR, DATA_FILE);
        try (
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream ois = new ObjectInputStream(bis);
        ) {
            list = (ArrayList<Drink>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 음료 리스트를 파일에 쓰기
     *
     * @param list 음료 데이터를 저장하는 리스트(ArrayList<Drink>)
     */
    public static void writeDataToFile(List<Drink> list) {
        File file = new File(DATA_DIR, DATA_FILE);
        try (
                FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                ObjectOutputStream oos = new ObjectOutputStream(bos);
        ) {
            oos.writeObject(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 음료 리스트 파일이 존재하면, 파일의 내용을 읽어서 리스트를 리턴
     * 음료 파일이 없다면, No elements 리스트를 리턴
     *
     * @return List<Drink>
     */
    public static ArrayList<Drink> initializeData() {
        ArrayList<Drink> list = new ArrayList<Drink>();

        File file = new File(DATA_DIR, DATA_FILE);
        if (file.exists()) {
            list = (ArrayList<Drink>) readDataFromFile();
        }

        return list;
    }

    //--- method
}