package version04;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import version04.CoffeeDao;
import version04.CoffeeDaoImpl;
import version04.Drink;
import version04.ChoiceInComposeCoffee.GetComposeMenuInformation;
import version04.ChoiceInPaiksCoffee.GetPaiksMenuInformation;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CoffeeMain implements GetComposeMenuInformation, GetPaiksMenuInformation {

    private JFrame frame;
    private JTextField textFieldMenuName;
    private JTextField textFieldPrice;
    private JTextField textFieldCalorie;
    private JTextField textFieldCaffeine;
    private JTextField textFieldSugar;
    private JLabel lblHeader;
    private JPanel headerPanel;
    private JPanel buttonPanel;
    private JButton btnComposeCoffee;
    private JButton btnPaiksCoffee;
    private JPanel bodyPanel;
    private JLabel lblMenuName;
    private JLabel lblPrice;
    private JLabel lblCalorie;
    private JLabel lblCaffeine;
    private JLabel lblSugar;

    private CoffeeDao dao = CoffeeDaoImpl.getInstance(); // 싱글턴으로 인스턴스 생성, dao로 메서드 접근 가능
    private ArrayList<Drink> composeCoffeeData = new ArrayList<Drink>();
    private ArrayList<Drink> paiksCoffeeData = new ArrayList<Drink>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CoffeeMain window = new CoffeeMain();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public CoffeeMain() {
        initialize();
        loadCoffeeDao();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("작은 커피추천기");
        frame.setBounds(700, 300, 478, 505);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // header
        headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 458, 61);
        frame.getContentPane().add(headerPanel);
        headerPanel.setLayout(new BorderLayout(0, 0));

        lblHeader = new JLabel("준비되어있다, 작은 음료추천 당신의");
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeader.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        headerPanel.add(lblHeader);
        // --- header

        // choice store
        buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 58, 458, 45);
        frame.getContentPane().add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        btnComposeCoffee = new JButton("컴포즈커피");
        btnComposeCoffee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChoiceInComposeCoffee.showComposeMenus(frame, CoffeeMain.this);
            }
        });
        btnComposeCoffee.setFont(new Font("굴림", Font.BOLD, 20));
        buttonPanel.add(btnComposeCoffee);

        btnPaiksCoffee = new JButton("빽다방");
        btnPaiksCoffee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChoiceInPaiksCoffee.showPaiksMenus(frame, CoffeeMain.this);
            }
        });

        btnPaiksCoffee.setFont(new Font("굴림", Font.BOLD, 20));
        buttonPanel.add(btnPaiksCoffee);
        // --- choice store

        // body
        bodyPanel = new JPanel();
        bodyPanel.setBounds(0, 132, 458, 285);
        frame.getContentPane().add(bodyPanel);
        bodyPanel.setLayout(null);
        // --- body

        // user choice, MenuName
        lblMenuName = new JLabel("당신의 픽");
        lblMenuName.setFont(new Font("굴림", Font.BOLD, 20));
        lblMenuName.setBounds(12, 23, 95, 28);
        bodyPanel.add(lblMenuName);

        textFieldMenuName = new JTextField();
        textFieldMenuName.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldMenuName.setFont(new Font("굴림", Font.BOLD, 16));
        textFieldMenuName.setBounds(240, 23, 206, 27);
        bodyPanel.add(textFieldMenuName);
        textFieldMenuName.setColumns(10);
        // --- user choice, MenuName

        // user choice, price
        lblPrice = new JLabel("얼마냐 하면은");
        lblPrice.setFont(new Font("굴림", Font.BOLD, 20));
        lblPrice.setBounds(12, 70, 138, 28);
        bodyPanel.add(lblPrice);

        textFieldPrice = new JTextField();
        textFieldPrice.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldPrice.setFont(new Font("굴림", Font.BOLD, 16));
        textFieldPrice.setColumns(10);
        textFieldPrice.setBounds(240, 70, 206, 27);
        bodyPanel.add(textFieldPrice);
        // --- user choice, price

        // user choice, calorie
        lblCalorie = new JLabel("칼로리가 얼마냐 하면은");
        lblCalorie.setFont(new Font("굴림", Font.BOLD, 20));
        lblCalorie.setBounds(12, 118, 226, 28);
        bodyPanel.add(lblCalorie);

        textFieldCalorie = new JTextField();
        textFieldCalorie.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldCalorie.setFont(new Font("굴림", Font.BOLD, 16));
        textFieldCalorie.setColumns(10);
        textFieldCalorie.setBounds(240, 118, 206, 27);
        bodyPanel.add(textFieldCalorie);
        // --- user choice, calorie

        // user choice, caffeine
        lblCaffeine = new JLabel("카페인이 얼마냐 하면은");
        lblCaffeine.setFont(new Font("굴림", Font.BOLD, 20));
        lblCaffeine.setBounds(12, 172, 226, 28);
        bodyPanel.add(lblCaffeine);

        textFieldCaffeine = new JTextField();
        textFieldCaffeine.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldCaffeine.setFont(new Font("굴림", Font.BOLD, 16));
        textFieldCaffeine.setColumns(10);
        textFieldCaffeine.setBounds(240, 172, 206, 27);
        bodyPanel.add(textFieldCaffeine);
        // --- user choice, caffeine

        // user choice, sugar
        lblSugar = new JLabel("당이 얼마냐 하면은");
        lblSugar.setFont(new Font("굴림", Font.BOLD, 20));
        lblSugar.setBounds(12, 223, 188, 28);
        bodyPanel.add(lblSugar);

        textFieldSugar = new JTextField();
        textFieldSugar.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldSugar.setFont(new Font("굴림", Font.BOLD, 16));
        textFieldSugar.setColumns(10);
        textFieldSugar.setBounds(240, 223, 206, 27);
        bodyPanel.add(textFieldSugar);
        // --- user choice, sugar

    }

    // method
    private void loadCoffeeDao() {
        composeCoffeeData = dao.setComposeDrinks();
        paiksCoffeeData = dao.setPaiksDrinks();
    }

    @Override
    public void getCompseMenuInformation(Drink drink) {
        textFieldMenuName.setText(drink.getName());
        textFieldPrice.setText(String.format("%S", drink.getPrice()));
        textFieldCalorie.setText(String.format("%S", drink.getCalorie()));
        textFieldCaffeine.setText(String.format("%S", drink.getCaffeine()));
        textFieldSugar.setText(String.format("%S", drink.getSugar()));
    }

    @Override
    public void getPaiksMenuInformation(Drink drink) {
        textFieldMenuName.setText(drink.getName());
        textFieldPrice.setText(String.format("%S", drink.getPrice()));
        textFieldCalorie.setText(String.format("%S", drink.getCalorie()));
        textFieldCaffeine.setText(String.format("%S", drink.getCaffeine()));
        textFieldSugar.setText(String.format("%S", drink.getSugar()));
    }

    // --- method
}
