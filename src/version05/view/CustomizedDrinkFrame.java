package version05.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.TableModel;

import version05.controller.CoffeeDao;
import version05.model.Coffee;

import static version05.model.Coffee.Entity.*;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class CustomizedDrinkFrame extends JFrame {

    // ------- JSwing Feild--------------------
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnShowCompose;
    private JButton btnShowPaiks;
    private JTable tableShowMenus;
    private JScrollPane scrollPane;
    private JScrollPane scrollPaneShowMenus;
    // ------- JSwing Feild--------------------

    // -------- Field ------------------
    private Component parentComponent;
    private static final String[] COLUMN_NAMES = {"메뉴이름", "가격", "칼로리", "카페인", "당류", "가게"};
    private DefaultTableModel tableModel;
    private CoffeeDao dao = CoffeeDao.getInstance();
    private JButton btnShowCustomized;
    private JButton btnInsert;
    private List<Coffee> menuList;
    private List<Coffee> favoriteMenuList;

    // -------- Field ------------------

    /**
     * Launch the application.
     */
    public static void showCustomizeFrame(Component parentComponent) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CustomizedDrinkFrame frame = new CustomizedDrinkFrame(parentComponent);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // ------------ Constructor ------------>
    public CustomizedDrinkFrame(Component parentComponent) {
        this.parentComponent = parentComponent;
        initialize();
    }
    // <----------- Constructor --------------

    // ------------ initialize ---------------->
    public void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("나만의 메뉴북 만들기");
        // --- Set frame where at --->
        int x = 0;
        int y = 0;
        if (parentComponent != null) {
            x = parentComponent.getX();
            y = parentComponent.getY();
        }
        setBounds(x + 100, y + 50, 523, 615);
        if (parentComponent == null) {
            setLocationRelativeTo(null);
        }
        // <--- Set frame where at ---
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // ------------- Read Section ---------------->
        ImageIcon composeIcon = new ImageIcon(
                new ImageIcon(getClass().getResource("/composeLogo.png")).getImage().getScaledInstance(68, 69, Image.SCALE_SMOOTH));
        btnShowCompose = new JButton(composeIcon);
        btnShowCompose.setBackground(new Color(255, 255, 255));
        btnShowCompose.setBorderPainted(false);
        btnShowCompose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO tableShowMenus에 기존 테이블 지우고(1) 컴포즈커피 테이블 전체 리스트업(2)
                readCafeDrinks(e);
            }
        });
        btnShowCompose.setBounds(12, 51, 68, 69);
        contentPane.add(btnShowCompose);

        ImageIcon paiksLogo = new ImageIcon(
                new ImageIcon(getClass().getResource("/paiksLogo.png")).getImage().getScaledInstance(68, 69, Image.SCALE_SMOOTH));
        btnShowPaiks = new JButton(paiksLogo);
        btnShowPaiks.setBorderPainted(false);
        btnShowPaiks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO tableshowMenus에 기존 테이블 지우고(1) 컴포즈커피 테이블 전체 리스트업(2)
                readCafeDrinks(e);
            }
        });
        btnShowPaiks.setBounds(92, 51, 68, 69);
        contentPane.add(btnShowPaiks);

        ImageIcon favoriteIcon = new ImageIcon(
                new ImageIcon(getClass().getResource("/favorite.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        btnShowCustomized = new JButton(favoriteIcon);
        btnShowCustomized.setBackground(new Color(255, 255, 255));
        btnShowCustomized.setBorderPainted(false);
        btnShowCustomized.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO FavoritedrinksTable 불러오기
                FavoriteDrinksFrame.showFavoriteDrinksFrame(parentComponent);
            }
        });
        btnShowCustomized.setBounds(419, 85, 35, 35);
        contentPane.add(btnShowCustomized);
        // <------------- Read Section ----------------

        // ------------ Insert to CustomizedTable --------------->
        ImageIcon plusIcon = new ImageIcon(
                new ImageIcon(getClass().getResource("/plus.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        btnInsert = new JButton(plusIcon);
        btnInsert.setBorderPainted(false); // 버튼 borderLine 지우는 코드
        btnInsert.setBackground(new Color(255, 255, 255));
        btnInsert.setHorizontalTextPosition(JButton.CENTER);
        btnInsert.setVerticalTextPosition(JButton.BOTTOM);
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableShowMenus.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(parentComponent, "선택되었는지 그리고 테이블이 올바른지 확인해주세요!", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int coffeeId = menuList.get(selectedRow).getId();
                // chatgpt가 제시한 코드 -->
                boolean isMenuAlreadyExist = dao.isMenuAlreadyExistInFavoritedrinks(coffeeId);

                if (isMenuAlreadyExist) {
                    // 이미 메뉴가 존재하는 경우에 사용자에게 알림
                    JOptionPane.showMessageDialog(contentPane, "이미 선택한 항목이 나만의 메뉴북에 존재합니다.", "알림",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // 메뉴가 존재하지 않는 경우에는 메뉴를 추가하고 사용자에게 알림
                    dao.insertFavoriteDrinkTable(coffeeId);
                    JOptionPane.showMessageDialog(contentPane, "선택한 항목이 나만의 메뉴북에 추가되었습니다.", "알림",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                // <-------

                // 성공 메시지를 표시

                tableShowMenus.clearSelection();
            }
        });
        btnInsert.setBounds(466, 85, 35, 35);
        contentPane.add(btnInsert);
        // <------------ Insert to CustomizedTable ---------------

//		// ------------ Delete from CustomizedTable ------------->
//		ImageIcon deleteIcon = new ImageIcon(
//				new ImageIcon("Emoji/delete.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
//		// <------------ Delete from CustomizedTable -------------

        // ---------------- Table ------------------>
        tableModel = new DefaultTableModel(null, COLUMN_NAMES); // 기본 모델을 생성하여 컬럼 이름 설정
        tableShowMenus = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableShowMenus.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        tableShowMenus.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPaneShowMenus = new JScrollPane(tableShowMenus); // 스크롤이 가능한 테이블을 생성
        scrollPaneShowMenus.setBounds(12, 126, 489, 428); // scrollPane을 contentPane에 추가
        contentPane.add(scrollPaneShowMenus);

//		tableModel = new DefaultTableModel(null, COLUMN_NAMES);
    }
    // <---------------- Table ------------------

    // <-------------- initialize ------------

    // -------------- Method ---------------->

    /**
     * 버튼 액션이 발생할 경우 발생한 버튼을 찾고 찾은 버튼을 cafeId변수에 담아 DRINKS 테이블에서 정보를 가져오는 SQL문의 조건으로
     * 사용함
     *
     * @param e
     */
    private void readCafeDrinks(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        String cafeId = null;
        if (btn == btnShowCompose) {
            cafeId = "C";
        } else if (btn == btnShowPaiks) {
            cafeId = "P";
        } else {
            return;
        }

        initializeTable(cafeId);
        scrollPaneShowMenus.getVerticalScrollBar().setValue(0);
    }

    private void setShowMenusTable() {
        tableModel = new DefaultTableModel(null, COLUMN_NAMES); // 테이블 모델 리셋.
        for (Coffee c : menuList) {
            // DB 테이블에서 검색한 레코드를 JTable에서 사용할 행 데이터로 변환.
            Object[] row = {c.getName(), c.getPrice(), c.getCalorie(), c.getCaffeine(), c.getSugar(), c.getCafeId()};
            tableModel.addRow(row); // 테이블 모델에 행 데이터를 추가.
        }
        tableShowMenus.setModel(tableModel); // JTable의 모델을 다시 세팅.
        tableShowMenus.getColumnModel().getColumn(0).setPreferredWidth(140);
        tableShowMenus.getColumnModel().getColumn(1).setPreferredWidth(35);
        tableShowMenus.getColumnModel().getColumn(2).setPreferredWidth(35);
        tableShowMenus.getColumnModel().getColumn(3).setPreferredWidth(35);
        tableShowMenus.getColumnModel().getColumn(4).setPreferredWidth(35);
    }

//	private void setShowCustomizedTable() {
//		tableModel = new DefaultTableModel(null, COLUMN_NAMES); // 테이블 모델 리셋.
//		for (Coffee c : favoriteMenuList) {
//			// DB 테이블에서 검색한 레코드를 JTable에서 사용할 행 데이터로 변환.
//			Object[] row = { c.getName(), c.getPrice(), c.getCalorie(), c.getCaffeine(), c.getSugar(), c.getCafeId() };
//			tableModel.addRow(row); // 테이블 모델에 행 데이터를 추가.
//		}
//		tableCustomized.setModel(tableModel); // JTable의 모델을 다시 세팅.
//	}

    private void initializeTable(String cafeId) {
        menuList = dao.readAllFromDrinksTableWithCafeId(cafeId);

        setShowMenusTable();
    }

//	private void initializedTable() {
//		favoriteMenuList = dao.readAllFromFavoritedrinksTable();
//
//		setShowCustomizedTable();
//	}

    // <-------------- Method ----------------
}
