import java.awt.*;

public class Bullet {
    public int x;
    public int y;

    private int speed = 10;
    private int angle;

    public Bullet(int x, int y, int angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void update() {
        x += (int) (Math.cos(Math.toRadians(angle)) * speed);
        y -= (int) (Math.sin(Math.toRadians(angle)) * speed);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x - 5, y - 5, 10, 10);
    }

    public boolean Colide(Tank tank) {
        int dx = tank.getX() - this.getX();
        int dy = tank.getY() - this.getY();

        return dx * dx + dy * dy <= 45 * 45;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

