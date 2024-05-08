package version05.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import version05.controller.CoffeeDao;
import version05.model.Coffee;
import javax.swing.JLabel;

public class FavoriteDrinksFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableCustomized;
    private JScrollPane scrollPane;
    private JScrollPane scrollPaneCustomized;

    // --------- Using Field ----------->
    private CoffeeDao dao = CoffeeDao.getInstance();
    private static final String[] COLUMN_NAMES = { "메뉴이름", "가격", "칼로리", "카페인", "당류", "가게" };
    private JButton btnShowCustomized;
    private DefaultTableModel tableModel;
    private JButton btnDelete;
    private List<Coffee> favoriteMenuList;
    private Component parentComponent;
    private JLabel lblNewLabel;

    // <--------- Using Field -----------
    /**
     * Launch the application.
     */
    public static void showFavoriteDrinksFrame(Component parentComponent) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FavoriteDrinksFrame frame = new FavoriteDrinksFrame(parentComponent);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // ----------- Cunstructor ------------>
    public FavoriteDrinksFrame(Component parentComponent) {
        this.parentComponent = parentComponent;
        initialize();
        initializedTable();
    }

    // <----------- Cunstructor ------------
    public void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // --- Set frame where at --->
        int x = 0;
        int y = 0;
        if (parentComponent != null) {
            x = parentComponent.getX();
            y = parentComponent.getY();
        }
        setBounds(x + 100, y + 50, 496, 562);
        if (parentComponent == null) {
            setLocationRelativeTo(null);
        }
        // <--- Set frame where at ---
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        // Table
        tableModel = new DefaultTableModel(null, COLUMN_NAMES); // 기본 모델을 생성하여 컬럼 이름 설정
        contentPane.setLayout(null);
        tableCustomized = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableCustomized.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        scrollPaneCustomized = new JScrollPane(tableCustomized); // 스크롤이 가능한 테이블을 생성
        scrollPaneCustomized.setBounds(12, 82, 452, 428); // scrollPane을 contentPane에 추가
        contentPane.add(scrollPaneCustomized);
        // Table

        // Delete
        ImageIcon deleteIcon = new ImageIcon(
                new ImageIcon(getClass().getResource("/delete.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        btnDelete = new JButton(deleteIcon);
        btnDelete.setBorderPainted(false);
        btnDelete.setBackground(new Color(255, 255, 255));
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO 선택한 열을 지우는 SQL문 실행
                int selectedRow = tableCustomized.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(contentPane, "삭제할 메뉴가 선택되었는지 확인해주세요!", "알림",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // 선택한 행에서 DRINK_ID
                int drinkId = favoriteMenuList.get(selectedRow).getId();

                // CoffeeDao를 사용하여 CUSTOMIZEDDRINK 테이블에서 해당 메뉴를 삭제
                dao.deleteFavoritedrinksTable(drinkId);

                // 성공 메시지 표시
                JOptionPane.showMessageDialog(contentPane, "선택한 항목이 나만의 메뉴북에서 삭제되었습니다.", "알림",
                        JOptionPane.INFORMATION_MESSAGE);

                // 테이블에서 선택한 행 삭제
                tableModel.removeRow(selectedRow);

                tableCustomized.clearSelection();
            }
        });
        btnDelete.setBounds(420, 32, 44, 40);
        contentPane.add(btnDelete);

        lblNewLabel = new JLabel("내가 만든 메뉴북");
        lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        lblNewLabel.setBounds(12, 32, 163, 40);
        contentPane.add(lblNewLabel);
        // Delete
    }

    // ------------ Method --------------->
    private void setShowCustomizedTable() {
        tableModel = new DefaultTableModel(null, COLUMN_NAMES); // 테이블 모델 리셋.
        for (Coffee c : favoriteMenuList) {
            // DB 테이블에서 검색한 레코드를 JTable에서 사용할 행 데이터로 변환.
            Object[] row = { c.getName(), c.getPrice(), c.getCalorie(), c.getCaffeine(), c.getSugar(), c.getCafeId() };
            tableModel.addRow(row); // 테이블 모델에 행 데이터를 추가.
        }
        tableCustomized.setModel(tableModel); // JTable의 모델을 다시 세팅.
        tableCustomized.getColumnModel().getColumn(0).setPreferredWidth(140);
        tableCustomized.getColumnModel().getColumn(1).setPreferredWidth(35);
        tableCustomized.getColumnModel().getColumn(2).setPreferredWidth(35);
        tableCustomized.getColumnModel().getColumn(3).setPreferredWidth(35);
        tableCustomized.getColumnModel().getColumn(4).setPreferredWidth(35);
    }

    private void initializedTable() {
        favoriteMenuList = dao.readAllFromFavoritedrinksTable();

        setShowCustomizedTable();
    }
}
