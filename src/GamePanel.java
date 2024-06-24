import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class GamePanel extends JPanel {
    private Tank tank1;

    public GamePanel() {
        setFocusable(true);
        setBackground(Color.BLACK);

        tank1 = new Tank(100, 300);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> tank1.move(0, -5);
                    case KeyEvent.VK_DOWN -> tank1.move(0, 5);
                    case KeyEvent.VK_LEFT -> tank1.move(-5, 0);
                    case KeyEvent.VK_RIGHT -> tank1.move(5, 0);
                }
                repaint();
            }
        });

        Timer timer = new Timer(1000 / 60, e -> repaint());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        tank1.draw(g);
    }
}