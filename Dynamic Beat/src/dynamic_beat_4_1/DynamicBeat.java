package dynamic_beat_4_1;

import javax.swing.*;

import dynamic_beat_4.Main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DynamicBeat extends JFrame {

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	
	private Image introBackground = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage();
	private JPanel mainPanel;
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	private JButton exitButton = new JButton(exitButtonBasicImage);

    private int mouseX, mouseY;

    public DynamicBeat() {
        setUndecorated(true);
        setTitle("Dynamic Beat");
        setSize(Main.SCREEN_WHIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 여기에 이미지를 그리는 작업 수행
                g.drawImage(introBackground, 0, 0, null);
            }
        };
        mainPanel.setLayout(null);
        add(mainPanel);

        exitButton = new JButton(new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png")));
        exitButton.setBounds(1245, 0, 30, 30);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png")));
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
                buttonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png")));
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
                buttonPressedMusic.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
        mainPanel.add(exitButton);

        menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
        menuBar.setBounds(0, 0, 1280, 30);
        menuBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - mouseX, y - mouseY);
            }
        });
        mainPanel.add(menuBar);

        Music introMusic = new Music("introMusic.mp3", true);
        introMusic.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        new DynamicBeat();
    }
}

