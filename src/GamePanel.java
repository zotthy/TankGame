import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class GamePanel extends JPanel {
    private Tank tank1;
    private Tank tank2;
    private List<Bullet> bullets;
    private Set<Integer> pressedKeys;

    public GamePanel() {
        setFocusable(true);
        tank1 = new Tank(100, 300);
        tank2 = new Tank(500, 300);
        bullets = new ArrayList<>();
        pressedKeys = new HashSet<>();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                pressedKeys.add(e.getKeyCode());
                handleKeyPress(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pressedKeys.remove(e.getKeyCode());
            }
        });

        Timer timer = new Timer(1000 / 60, e -> {
            handlePressedKeys();
            bullets.forEach(Bullet::update);
            repaint();
        });
        timer.start();
    }

    private void handleKeyPress(int keyCode) {
        int targetAngle;
        switch (keyCode) {
            case KeyEvent.VK_SPACE:
                int correctedAngle = (tank1.getAngle() + 90) % 360;
                bullets.add(new Bullet(tank1.getX() + 45, tank1.getY() + 45, correctedAngle));
                break;
            case KeyEvent.VK_SHIFT:
                int correctedAngle2 = (tank2.getAngle() + 90) % 360;
                bullets.add(new Bullet(tank2.getX() + 45, tank2.getY() + 45, correctedAngle2));
                break;
        }
    }

    private void handlePressedKeys() {
        for (int keyCode : pressedKeys) {
            int targetAngle;
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    targetAngle = 0;
                    tank1.move(0, -5,TankWars.x, TankWars.y, targetAngle - tank1.getAngle());
                    break;
                case KeyEvent.VK_DOWN:
                    targetAngle = 180;
                    tank1.move(0, 5, TankWars.x, TankWars.y, targetAngle - tank1.getAngle());
                    break;
                case KeyEvent.VK_LEFT:
                    targetAngle = 90;
                    tank1.move(-5, 0, TankWars.x, TankWars.y, targetAngle - tank1.getAngle());
                    break;
                case KeyEvent.VK_RIGHT:
                    targetAngle = 270;
                    tank1.move(5, 0, TankWars.x, TankWars.y, targetAngle - tank1.getAngle());
                    break;
                case KeyEvent.VK_W:
                    targetAngle = 0;
                    tank2.move(0, -5, TankWars.x, TankWars.y, targetAngle - tank2.getAngle());
                    break;
                case KeyEvent.VK_S:
                    targetAngle = 180;
                    tank2.move(0, 5, TankWars.x, TankWars.y, targetAngle - tank2.getAngle());
                    break;
                case KeyEvent.VK_A:
                    targetAngle = 90;
                    tank2.move(-5, 0, TankWars.x, TankWars.y, targetAngle - tank2.getAngle());
                    break;
                case KeyEvent.VK_D:
                    targetAngle = 270;
                    tank2.move(5, 0, TankWars.x, TankWars.y, targetAngle - tank2.getAngle());
                    break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        tank1.draw(g);
        tank2.draw(g);
        bullets.forEach(bullet -> bullet.draw(g));
    }
}
