package version05.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;

import version05.controller.CoffeeDao;
import version05.model.Coffee;
import static version05.model.Coffee.Entity.*;

import javax.swing.event.ChangeEvent;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class RecommendRandomDrinks extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JSlider sugarSlider;
    private JSlider caffeineSlider;

    private JTextField textFieldCaffeine;
    private JTextField textFieldSugar;
    private JButton btnSubmit;
    private JRadioButton rdbtnCompose;
    private JRadioButton rdbtnPaiks;
    private JPanel radiobtnPanel;
    private ButtonGroup buttonGroup = new ButtonGroup();

    // -------- Field ------------------
    private Component parentComponent;
    private CoffeeDao dao = CoffeeDao.getInstance();
    private double sugar;
    private double caffeine;
    private JRadioButton rdbtnfavorite;
    // -------- Field ------------------

    // -------- New Field ------------
    private JLabel cupImageLabel;
    private JLabel lblCup;
    private JTextField textFieldCaffeine1;
    private JTextField textFieldCaffeine2;
    private JTextField textFieldCaffeine3;
    private JTextField textFieldCaffeine4;
    private JTextField textFieldCaffeine5;
    private JTextField textFieldCaffeine6;
    private JTextField textFieldSugar1;
    private JTextField textFieldSugar2;
    private JTextField textFieldSugar3;
    private JTextField textFieldSugar4;
    private JTextField textFieldSugar5;
    private JTextField textFieldSugar6;
    private JTextField textFieldSugar7;
    private JTextField textFieldSugar8;
    private JTextField textFieldSugar9;
    private JTextField textFieldSugar10;
    // -------- New Field ------------

    /**
     * Launch the application.
     */
    public static void showRandomDrinks(Component parentComponent) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RecommendRandomDrinks frame = new RecommendRandomDrinks(parentComponent);
                    JOptionPane.showMessageDialog(parentComponent, "1. 창 상단에서 추천받을 가게를 골라주세요. \n2. 카페인은 450을, 설탕량은 100을 넘길 수 없습니다. \n3. 카페인과 설탕량은 펌프하여 추가할 수 있습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // ------------ Constructor ------------
    public RecommendRandomDrinks(Component parentComponent) {
        this.parentComponent = parentComponent;
        initialize();

    }
    // --------------------------------

    // ------------ Method ----------------
    public void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("메뉴추천 받기");
        // --- Set frame where at ---
        int x = 0;
        int y = 0;
        if (parentComponent != null) {
            x = parentComponent.getX();
            y = parentComponent.getY();
        }
        setBounds(x + 100, y + 50, 448, 439);
        if (parentComponent == null) {
            setLocationRelativeTo(null);
        }
        // --- Set frame where at ---
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // ------------------- Caffeine Slider ------------------
        caffeineSlider = new JSlider();
        caffeineSlider.setBackground(new Color(255, 255, 255));
        caffeineSlider.setValue(caffeineSlider.getMaximum()); // 슬라이더를 최대값으로 설정하여 항상 최상단에서 시작
        caffeineSlider.addChangeListener(new ChangeListener() {
            boolean toggled = false; // 토글 상태

            public void stateChanged(ChangeEvent e) {
                int value = caffeineSlider.getValue();
                if (!toggled && value <= caffeineSlider.getMaximum() * 0.3) {
                    if (caffeine >= 450) {
                        caffeineSlider.setValue(caffeineSlider.getMaximum());
                        JOptionPane.showMessageDialog(contentPane, "더 이상의 샷추가는 어렵습니다.", "알림",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    caffeine += 75;
//					System.out.println("Caffeine: " + caffeine); // 최종본에서 삭제
                    textFieldCaffeine.setText(String.valueOf(caffeine));
                    toggled = true; // 토글 상태 변경
                    fillUpTheMugCaffeine(caffeine);

                } else if (toggled && value > caffeineSlider.getMaximum() * 0.3) {
                    toggled = false; // 토글 상태 변경
                }

            }

        });
        caffeineSlider.setOrientation(SwingConstants.VERTICAL);
        caffeineSlider.setBounds(41, 136, 80, 183);
        contentPane.add(caffeineSlider);
        // ------------------- Caffeine Slider ------------------

        // ---------------- Show Caffeine -----------------------
        textFieldCaffeine = new JTextField();
        textFieldCaffeine.setBorder(null);
        textFieldCaffeine.setBackground(new Color(255, 255, 255));
        textFieldCaffeine.setEditable(false);
        textFieldCaffeine.setText("0");
        textFieldCaffeine.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldCaffeine.setFont(new Font("굴림", Font.BOLD, 20));
        textFieldCaffeine.setBounds(41, 105, 80, 30);
        contentPane.add(textFieldCaffeine);
        textFieldCaffeine.setColumns(10);
        // ---------------- Show Caffeine -----------------------

        // ------------------- Sugar Slider ------------------
        sugarSlider = new JSlider();
        sugarSlider.setBackground(new Color(255, 255, 255));
        sugarSlider.setValue(sugarSlider.getMaximum()); // 슬라이더를 최대값으로 설정하여 항상 최상단에서 시작
        sugarSlider.addChangeListener(new ChangeListener() {
            boolean toggled = false; // 토글 상태

            public void stateChanged(ChangeEvent e) {
                int value = sugarSlider.getValue();
                if (!toggled && value <= sugarSlider.getMaximum() * 0.3) {
                    if (sugar >= 100) {
                        sugarSlider.setValue(caffeineSlider.getMaximum());
                        JOptionPane.showMessageDialog(contentPane, "더 이상의 설탕추가는 어렵습니다.", "알림",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    sugar += 10;
//					System.out.println("Sugar: " + sugar); // 최종본에서 삭제
                    textFieldSugar.setText(String.valueOf(sugar));
                    toggled = true; // 토글 상태 변경
                    fillUpTheMugSugar(sugar);
                } else if (toggled && value > sugarSlider.getMaximum() * 0.3) {
                    toggled = false; // 토글 상태 변경
                }
            }
        });
        sugarSlider.setOrientation(SwingConstants.VERTICAL);
        sugarSlider.setBounds(165, 136, 72, 183);
        contentPane.add(sugarSlider);
        // ------------------- Sugar Slider ------------------

        // ---------------- Show Sugar -----------------------
        textFieldSugar = new JTextField();
        textFieldSugar.setBorder(null);
        textFieldSugar.setBackground(new Color(255, 255, 255));
        textFieldSugar.setEditable(false);
        textFieldSugar.setText("0");
        textFieldSugar.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldSugar.setFont(new Font("굴림", Font.BOLD, 20));
        textFieldSugar.setColumns(10);
        textFieldSugar.setBounds(165, 105, 80, 30);
        contentPane.add(textFieldSugar);
        // ---------------- Show Sugar -----------------------

        // ---------------- Cup -----------------
        lblCup = new JLabel("");
        lblCup.setIcon(new ImageIcon(
                new ImageIcon(getClass().getResource("/cup.png")).getImage().getScaledInstance(120, 180, Image.SCALE_SMOOTH)));
        lblCup.setBounds(315, 136, 120, 180);
        contentPane.add(lblCup);
        // ---------------- Cup -----------------

        // -------------- Fill the caffeine into cup ----------------
        textFieldCaffeine1 = new JTextField();
        textFieldCaffeine1.setBorder(null);
        textFieldCaffeine1.setBackground(new Color(255, 255, 255));
        textFieldCaffeine1.setBounds(334, 298, 55, 5);
        contentPane.add(textFieldCaffeine1);
        textFieldCaffeine1.setColumns(10);

        textFieldCaffeine2 = new JTextField();
        textFieldCaffeine2.setColumns(10);
        textFieldCaffeine2.setBorder(null);
        textFieldCaffeine2.setBackground(new Color(255, 255, 255));
        textFieldCaffeine2.setBounds(334, 293, 55, 5);
        contentPane.add(textFieldCaffeine2);

        textFieldCaffeine3 = new JTextField();
        textFieldCaffeine3.setColumns(10);
        textFieldCaffeine3.setBorder(null);
        textFieldCaffeine3.setBackground(new Color(255, 255, 255));
        textFieldCaffeine3.setBounds(334, 288, 55, 5);
        contentPane.add(textFieldCaffeine3);

        textFieldCaffeine4 = new JTextField();
        textFieldCaffeine4.setColumns(10);
        textFieldCaffeine4.setBorder(null);
        textFieldCaffeine4.setBackground(new Color(255, 255, 255));
        textFieldCaffeine4.setBounds(334, 283, 55, 5);
        contentPane.add(textFieldCaffeine4);

        textFieldCaffeine5 = new JTextField();
        textFieldCaffeine5.setColumns(10);
        textFieldCaffeine5.setBorder(null);
        textFieldCaffeine5.setBackground(new Color(255, 255, 255));
        textFieldCaffeine5.setBounds(334, 278, 55, 5);
        contentPane.add(textFieldCaffeine5);

        textFieldCaffeine6 = new JTextField();
        textFieldCaffeine6.setColumns(10);
        textFieldCaffeine6.setBorder(null);
        textFieldCaffeine6.setBackground(new Color(255, 255, 255));
        textFieldCaffeine6.setBounds(334, 273, 55, 5);
        contentPane.add(textFieldCaffeine6);
        // -------------- Fill the caffeine into cup ----------------

        // ------------- Fill the sugar into cup ------------------
        textFieldSugar1 = new JTextField();
        textFieldSugar1.setColumns(10);
        textFieldSugar1.setBorder(null);
        textFieldSugar1.setBackground(new Color(255, 255, 255));
        textFieldSugar1.setBounds(334, 268, 55, 5);
        contentPane.add(textFieldSugar1);

        textFieldSugar2 = new JTextField();
        textFieldSugar2.setColumns(10);
        textFieldSugar2.setBorder(null);
        textFieldSugar2.setBackground(new Color(255, 255, 255));
        textFieldSugar2.setBounds(334, 263, 55, 5);
        contentPane.add(textFieldSugar2);

        textFieldSugar3 = new JTextField();
        textFieldSugar3.setColumns(10);
        textFieldSugar3.setBorder(null);
        textFieldSugar3.setBackground(new Color(255, 255, 255));
        textFieldSugar3.setBounds(334, 258, 55, 5);
        contentPane.add(textFieldSugar3);

        textFieldSugar4 = new JTextField();
        textFieldSugar4.setColumns(10);
        textFieldSugar4.setBorder(null);
        textFieldSugar4.setBackground(new Color(255, 255, 255));
        textFieldSugar4.setBounds(334, 253, 55, 5);
        contentPane.add(textFieldSugar4);

        textFieldSugar5 = new JTextField();
        textFieldSugar5.setColumns(10);
        textFieldSugar5.setBorder(null);
        textFieldSugar5.setBackground(new Color(255, 255, 255));
        textFieldSugar5.setBounds(334, 248, 55, 5);
        contentPane.add(textFieldSugar5);

        textFieldSugar6 = new JTextField();
        textFieldSugar6.setColumns(10);
        textFieldSugar6.setBorder(null);
        textFieldSugar6.setBackground(new Color(255, 255, 255));
        textFieldSugar6.setBounds(334, 243, 55, 5);
        contentPane.add(textFieldSugar6);

        textFieldSugar7 = new JTextField();
        textFieldSugar7.setColumns(10);
        textFieldSugar7.setBorder(null);
        textFieldSugar7.setBackground(new Color(255, 255, 255));
        textFieldSugar7.setBounds(334, 238, 55, 5);
        contentPane.add(textFieldSugar7);

        textFieldSugar8 = new JTextField();
        textFieldSugar8.setColumns(10);
        textFieldSugar8.setBorder(null);
        textFieldSugar8.setBackground(new Color(255, 255, 255));
        textFieldSugar8.setBounds(334, 233, 55, 5);
        contentPane.add(textFieldSugar8);

        textFieldSugar9 = new JTextField();
        textFieldSugar9.setColumns(10);
        textFieldSugar9.setBorder(null);
        textFieldSugar9.setBackground(new Color(255, 255, 255));
        textFieldSugar9.setBounds(334, 228, 55, 5);
        contentPane.add(textFieldSugar9);

        textFieldSugar10 = new JTextField();
        textFieldSugar10.setColumns(10);
        textFieldSugar10.setBorder(null);
        textFieldSugar10.setBackground(new Color(255, 255, 255));
        textFieldSugar10.setBounds(334, 223, 55, 5);
        contentPane.add(textFieldSugar10);
        // ------------- Fill the sugar into cup ------------------

        // ---------------- Submit data to DB and show result --------------------
        btnSubmit = new JButton("추천받기");
        btnSubmit.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cafeName = null;
                if (rdbtnCompose.isSelected()) {
                    cafeName = "C";
                    List<Coffee> coffees = dao.readWithOption(textFieldCaffeine.getText(), textFieldSugar.getText(),
                            cafeName);
                    ShowResult.showResult(parentComponent, coffees);
                } else if (rdbtnPaiks.isSelected()) {
                    cafeName = "P";
                    List<Coffee> coffees = dao.readWithOption(textFieldCaffeine.getText(), textFieldSugar.getText(),
                            cafeName);
                    ShowResult.showResult(parentComponent, coffees);
                } else if (rdbtnfavorite.isSelected()) {
                    List<Coffee> coffees = dao.readWithOption(textFieldCaffeine.getText(), textFieldSugar.getText());
                    ShowResult.showResult(parentComponent, coffees);
                } else {
                    JOptionPane.showMessageDialog(RecommendRandomDrinks.this, "가게는 꼭 골라주세요!", "경고",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                dispose();
            }
        });
        btnSubmit.setBounds(125, 338, 156, 52);
        contentPane.add(btnSubmit);
        // ---------------- Submit data to DB and show result --------------------

        // ------------------------ Radio button group ---------------------------
        radiobtnPanel = new JPanel();
        radiobtnPanel.setBackground(new Color(255, 255, 255));
        radiobtnPanel.setBounds(0, 10, 435, 42);
        contentPane.add(radiobtnPanel);
        radiobtnPanel.setLayout(null);

        rdbtnCompose = new JRadioButton("컴포즈커피");
        rdbtnCompose.setBounds(27, 6, 101, 23);
        rdbtnCompose.setBackground(new Color(255, 255, 255));
        radiobtnPanel.add(rdbtnCompose);
        rdbtnCompose.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnCompose.setVerticalAlignment(SwingConstants.TOP);
        buttonGroup.add(rdbtnCompose);

        rdbtnPaiks = new JRadioButton("빽다방");
        rdbtnPaiks.setBounds(163, 6, 71, 23);
        rdbtnPaiks.setBackground(new Color(255, 255, 255));
        radiobtnPanel.add(rdbtnPaiks);
        rdbtnPaiks.setVerticalAlignment(SwingConstants.TOP);
        rdbtnPaiks.setHorizontalAlignment(SwingConstants.CENTER);
        buttonGroup.add(rdbtnPaiks);

        rdbtnfavorite = new JRadioButton("나만의 메뉴북");
        rdbtnfavorite.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnfavorite.setBounds(281, 6, 116, 23);
        rdbtnfavorite.setBackground(new Color(255, 255, 255));
        radiobtnPanel.add(rdbtnfavorite);
        rdbtnPaiks.setVerticalAlignment(SwingConstants.TOP);
        rdbtnPaiks.setHorizontalAlignment(SwingConstants.CENTER);
        buttonGroup.add(rdbtnfavorite);

        JLabel lblHowMuchCaffeine = new JLabel("카페인량");
        lblHowMuchCaffeine.setEnabled(false);
        lblHowMuchCaffeine.setHorizontalAlignment(SwingConstants.CENTER);
        lblHowMuchCaffeine.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        lblHowMuchCaffeine.setBounds(37, 62, 96, 42);
        contentPane.add(lblHowMuchCaffeine);

        JLabel lblHowMuchSugar = new JLabel("설탕량");
        lblHowMuchSugar.setHorizontalAlignment(SwingConstants.CENTER);
        lblHowMuchSugar.setEnabled(false);
        lblHowMuchSugar.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        lblHowMuchSugar.setBounds(157, 62, 96, 42);
        contentPane.add(lblHowMuchSugar);

        // ------------------------ Radio button group ---------------------------

    }

    // ------------ Method -------------->
    private void fillUpTheMugCaffeine(double caffeine) {
        if (caffeine == 75) {
            textFieldCaffeine1.setBackground(new Color(0, 0, 0));
        } else if (caffeine == 150) {
            textFieldCaffeine2.setBackground(new Color(0, 0, 0));
        } else if (caffeine == 225) {
            textFieldCaffeine3.setBackground(new Color(0, 0, 0));
        } else if (caffeine == 300) {
            textFieldCaffeine4.setBackground(new Color(0, 0, 0));
        } else if (caffeine == 375) {
            textFieldCaffeine5.setBackground(new Color(0, 0, 0));
        } else if (caffeine == 450) {
            textFieldCaffeine6.setBackground(new Color(0, 0, 0));
        }
    }

    private void fillUpTheMugSugar(double sugar) {
        if (sugar == 10) {
            textFieldSugar1.setBackground(new Color(135, 185, 225));
        } else if (sugar == 20) {
            textFieldSugar2.setBackground(new Color(135, 185, 225));
        } else if (sugar == 30) {
            textFieldSugar3.setBackground(new Color(135, 185, 225));
        } else if (sugar == 40) {
            textFieldSugar4.setBackground(new Color(135, 185, 225));
        } else if (sugar == 50) {
            textFieldSugar5.setBackground(new Color(135, 185, 225));
        } else if (sugar == 60) {
            textFieldSugar6.setBackground(new Color(135, 185, 225));
        } else if (sugar == 70) {
            textFieldSugar7.setBackground(new Color(135, 185, 225));
        } else if (sugar == 80) {
            textFieldSugar8.setBackground(new Color(135, 185, 225));
        } else if (sugar == 90) {
            textFieldSugar9.setBackground(new Color(135, 185, 225));
        } else if (sugar == 100) {
            textFieldSugar10.setBackground(new Color(135, 185, 225));
        }
    }
}
