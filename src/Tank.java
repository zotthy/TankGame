import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tank {
    private int x;
    private int y;
    private int angle;

    private BufferedImage tankImage;
    private final int SIZE = 20;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        this.angle = 0;
        try {
            tankImage = ImageIO.read(new File("src/Assets/tankmodel.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        double rotationRequired = Math.toRadians(-angle);
        double locationX = tankImage.getWidth() / 2;
        double locationY = tankImage.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        g2d.drawImage(op.filter(tankImage, null), x - SIZE / 2, y - SIZE / 2, null);

    }
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void rotate(int dAngle) {
        angle += dAngle;
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
