package version05.view;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class CoffeeMain {

    private JFrame frame;
    private JButton btnRandom;
    private JButton btnCustomized;
    private JLabel lblNewLabel;
    private JLabel imageLabel;

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
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.setTitle("오늘 커피 모마심?");
        frame.setBounds(700, 300, 425, 380);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Load image
        ImageIcon icon = new ImageIcon(getClass().getResource("/main.png"));
        Image img = icon.getImage().getScaledInstance(175, 180, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);

        // Create JLabel to display image
        imageLabel = new JLabel(scaledIcon);
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ImageIcon thanksIcon = new ImageIcon(getClass().getResource("/pedroRacoon.gif"));
                JOptionPane.showMessageDialog(frame, "", "찾으셨군요!", JOptionPane.INFORMATION_MESSAGE, thanksIcon);
            }
        });

        imageLabel.setBounds(234, 10, 175, 180);
        frame.getContentPane().add(imageLabel);

        //--------- Go to recommend drink menu for random ----------
        btnRandom = new JButton("메뉴추천받으러 가기");
        btnRandom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Create random recommend Frame
                RecommendRandomDrinks.showRandomDrinks(frame);
            }
        });
        btnRandom.setBounds(54, 138, 161, 45);
        frame.getContentPane().add(btnRandom);
        // ------------------------------------------------------------

        // --------------- Go to customized menus ---------------------
        btnCustomized = new JButton("메뉴확인 및 나만의 메뉴북 만들러 가기");
        btnCustomized.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO create customized coffee menus
                CustomizedDrinkFrame.showCustomizeFrame(frame);
            }
        });
        btnCustomized.setBounds(54, 212, 256, 45);
        frame.getContentPane().add(btnCustomized);

        lblNewLabel = new JLabel("오늘 커피 모마심?");
        lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        lblNewLabel.setBounds(54, 62, 282, 56);
        frame.getContentPane().add(lblNewLabel);
        // ------------------------------------------------------------
    }
}
