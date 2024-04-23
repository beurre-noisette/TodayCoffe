package version04;

import java.util.Random;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChoiceInComposeCoffee extends JFrame {

    // interface
    public static interface GetComposeMenuInformation {
        public void getCompseMenuInformation(Drink drink);
    }
    // --- interface

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnCaffeine;
    private JButton btnAllbeverage;
    private JButton btnDeCaffeine;

    private Component parentComponent;
    private GetComposeMenuInformation app;
    private CoffeeDao dao = CoffeeDaoImpl.getInstance();
    private Random random = new Random();

    /**
     * Launch the application.
     */
    public static void showComposeMenus(Component parentComponent, GetComposeMenuInformation app) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChoiceInComposeCoffee frame = new ChoiceInComposeCoffee(parentComponent, app);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor
     */
    public ChoiceInComposeCoffee(Component parentComponent, GetComposeMenuInformation app) {
        this.parentComponent = parentComponent;
        this.app = app;
        initialize();
    }

    /**
     * Create the frame.
     */
    public void initialize() {
        setTitle("컴포즈커피에서 골라볼래요?");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // set CreateFrame where at
        int x = 0;
        int y = 0;
        if (parentComponent != null) {
            x = parentComponent.getX();
            y = parentComponent.getY();
        }
        setBounds(x, y, 368, 300);

        if (parentComponent == null) {
            setLocationRelativeTo(null);
        }
        // --- set CreateFrame where at

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Caffeine
        btnCaffeine = new JButton("카페인 없인 못살아");
        btnCaffeine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choiceInCaffeine();
            }
        });
        btnCaffeine.setFont(new Font("굴림", Font.BOLD, 20));
        btnCaffeine.setBounds(12, 25, 328, 58);
        contentPane.add(btnCaffeine);
        // --- Caffeine

        // DeCaffeine
        btnDeCaffeine = new JButton("카페인 없이 잘살아");
        btnDeCaffeine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choiceInDeCaffeine();
            }
        });
        btnDeCaffeine.setFont(new Font("굴림", Font.BOLD, 20));
        btnDeCaffeine.setBounds(12, 93, 328, 69);
        contentPane.add(btnDeCaffeine);
        // --- DeCafffeine

        // AllBeverage
        btnAllbeverage = new JButton("알잘딱깔센");
        btnAllbeverage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choiceInAllBeverage();
            }
        });
        btnAllbeverage.setFont(new Font("굴림", Font.BOLD, 20));
        btnAllbeverage.setBounds(12, 172, 328, 79);
        contentPane.add(btnAllbeverage);
        //--- AllBeverage

    }

    // method
    private void choiceInCaffeine() {
        int index = random.nextInt(0, 12);
        Drink drink = dao.readCompose(index);
        app.getCompseMenuInformation(drink);
        dispose();
    }

    private void choiceInDeCaffeine() {
        int index = random.nextInt(13, 53);
        Drink drink = dao.readCompose(index);
        app.getCompseMenuInformation(drink);
        dispose();
    }

    private void choiceInAllBeverage() {
        int index = random.nextInt(0, 53);
        Drink drink = dao.readCompose(index);
        app.getCompseMenuInformation(drink);
        dispose();
    }
    // --- method
}
