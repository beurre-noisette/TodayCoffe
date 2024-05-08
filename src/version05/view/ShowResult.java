package version05.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import version05.model.Coffee;

import java.awt.Font;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ShowResult extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private Component parentComponent;
    private List<Coffee> coffees;
    private DefaultTableModel tableModel;
    private static final String[] COLUMN_NAMES = { "메뉴이름", "가격", "칼로리", "카페인", "당류", "가게"};
    private JTable table;
    private JScrollPane scrollPane;

    /**
     * Launch the application.
     */
    public static void showResult(Component parentComponent, List<Coffee> coffees) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShowResult frame = new ShowResult(parentComponent, coffees);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // ------------ Constructor ------------
    public ShowResult(Component parentComponent, List<Coffee> coffees) {
        this.parentComponent = parentComponent;
        this.coffees = coffees;
        initialize();
        setTable(coffees);
    }
    // ------------ Constructor ------------

    // ------------ Initialize ----------------
    public void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("추천된 메뉴입니다!");
        // --- Set frame where at ---
        int x = 0;
        int y = 0;
        if (parentComponent != null) {
            x = parentComponent.getX();
            y = parentComponent.getY();
        }
        setBounds(x + 100, y + 50, 544, 350);
        if (parentComponent == null) {
            setLocationRelativeTo(null);
        }
        // --- Set frame where at ---
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // ---------------- Table ------------------
        tableModel = new DefaultTableModel(null, COLUMN_NAMES); // 기본 모델을 생성하여 컬럼 이름 설정
        table = new JTable(tableModel); // table 생성시 모델을 지정
        table.setEnabled(false);
        table.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        scrollPane = new JScrollPane(table); // 스크롤이 가능한 테이블을 생성
        scrollPane.setBounds(0, 10, 527, 291); // scrollPane을 contentPane에 추가
        contentPane.add(scrollPane);
        // ---------------- Table ------------------

    }
    // ------------ Initialize ----------------

    // -------------------- Method ------------------
    private void setTable(List<Coffee> coffees) {
        tableModel = new DefaultTableModel(null, COLUMN_NAMES); // 테이블 모델 리셋.
        for (Coffee c : coffees) {
            // DB 테이블에서 검색한 레코드를 JTable에서 사용할 행 데이터로 변환.
            Object[] row = { c.getName(), c.getPrice(), c.getCalorie(), c.getCaffeine(), c.getSugar(), c.getCafeId() };
            tableModel.addRow(row); // 테이블 모델에 행 데이터를 추가.
        }
        table.setModel(tableModel); // JTable의 모델을 다시 세팅.
        table.getColumnModel().getColumn(0).setPreferredWidth(140);
        table.getColumnModel().getColumn(1).setPreferredWidth(35);
        table.getColumnModel().getColumn(2).setPreferredWidth(35);
        table.getColumnModel().getColumn(3).setPreferredWidth(35);
        table.getColumnModel().getColumn(4).setPreferredWidth(35);
    }
    // -------------------- Method ------------------
}
