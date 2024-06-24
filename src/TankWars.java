import javax.swing.*;

public class TankWars extends JFrame {
    private final int x = 800;
    private final int y = 600;

    public TankWars() {
        super("Tank WARS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(x,y);
        setLocationRelativeTo(null);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem startMenuItem = new JMenuItem("Start");
        startMenuItem.addActionListener(e -> startGame());

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));

        menu.add(startMenuItem);
        menu.add(exitMenuItem);

        menuBar.add(menu);
        setJMenuBar(menuBar);

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