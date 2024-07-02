import java.awt.*;

public class Bullet {
    public int x;
    public int y;

    private int speed = 10;
    private int angle;
    private int tankID;

    public Bullet(int x, int y, int angle, int tankID) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.tankID = tankID;
    }

    public void update() {
        x += (int) (Math.cos(Math.toRadians(angle)) * speed);
        y -= (int) (Math.sin(Math.toRadians(angle)) * speed);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x - 5, y - 5, 10, 10);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getTankID() {
        return tankID;
    }

    public void setTankID(int tankID) {
        this.tankID = tankID;
    }

    public int getY() {
        return y;
    }
}

