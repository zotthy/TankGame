import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TankWars extends JFrame {
    public static final int x = 1000;
    public static final int y = 800;

    public TankWars() {
        super("Tank WARS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        setLocationRelativeTo(null);

        ImageIcon bgImageIcon = new ImageIcon("src/Assets/Main.png");
        Image bgImage = bgImageIcon.getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH);
        ImageIcon scaledBgImageIcon = new ImageIcon(bgImage);

        JLabel backgroundLabel = new JLabel(scaledBgImageIcon);

        JLabel textStart = new JLabel("Start");
        textStart.setBounds(770, 260, 100, 100);
        Font font = textStart.getFont();
        textStart.setFont(new Font(font.getFontName(), Font.BOLD, 24));
        textStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                textStart.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                textStart.setForeground(Color.BLACK);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                startGame();
            }
        });

        JLabel textExit = new JLabel("Exit");
        textExit.setBounds(770, 360, 100, 100);
        textExit.setFont(new Font(font.getFontName(), Font.BOLD, 24));
        textExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                textExit.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                textExit.setForeground(Color.BLACK);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        setContentPane(backgroundLabel);
        add(textStart);
        add(textExit);

        setVisible(true);
    }
    private void startGame() {
        GamePanel gamePanel = new GamePanel();
        setContentPane(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        revalidate();
        repaint();
    }

}