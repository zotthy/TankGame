import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class Tank {
    private int x;
    private int y;
    private int angle;
    private int tankId;

    private BufferedImage tankImage;
    private final int SIZE = 20;
    private static final int TANK_SIZE = 50;

    public Tank(int x, int y, int tankId) {
        this.x = x;
        this.y = y;
        this.angle = 0;
        this.tankId = tankId;
        try {
            tankImage = ImageIO.read(new File("src/Assets/Tank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        double rotationRequired = Math.toRadians(-angle);
        double locationX = tankImage.getWidth() / 6;
        double locationY = tankImage.getHeight() / 6;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        // Scale image
        double scaleFactor = 0.3;
        BufferedImage scaledTankImage = new BufferedImage((int) (tankImage.getWidth() * scaleFactor), (int) (tankImage.getHeight() * scaleFactor), tankImage.getType());
        Graphics2D g2dScaled = scaledTankImage.createGraphics();
        g2dScaled.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2dScaled.drawImage(tankImage, 0, 0, scaledTankImage.getWidth(), scaledTankImage.getHeight(), null);
        g2dScaled.dispose();

        g2d.drawImage(op.filter(scaledTankImage, null), x - SIZE / 4, y - SIZE / 4, null);

    }

    public void move(int dx, int dy, int panelWidth, int panelHeight, int angleDiff, List<Wall> walls) {
        int newX = x + dx;
        int newY = y + dy;

        if (newX < 0 || newX + TANK_SIZE > panelWidth || newY < 0 || newY + TANK_SIZE > panelHeight) {
            return;
        }

        for (Wall wall : walls) {
            if (checkCollisionWithWall(newX, newY, wall)) {
                return;
            }
        }

        x = newX;
        y = newY;
        angle = (angle + angleDiff) % 360;

        System.out.println("x:" + x);
        System.out.println("y:" + y);
        System.out.println("r:" + angle);
    }

    private boolean checkCollisionWithWall(int newX, int newY, Wall wall) {
        final int WALL_SIZE = 70;
        boolean xOverlap = (wall.getX() < newX + TANK_SIZE) && (wall.getX() + WALL_SIZE > newX);
        boolean yOverlap = (wall.getY() < newY + TANK_SIZE) && (wall.getY() + WALL_SIZE > newY);

        return xOverlap && yOverlap;
    }

    public int getTankId() {
        return tankId;
    }

    public void setTankId(int tankId) {
        this.tankId = tankId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAngle() {
        return angle;
    }
}
