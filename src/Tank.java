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
    private int tankId;

    private BufferedImage tankImage;
    private final int SIZE = 20;

    public Tank(int x, int y,int tankId) {
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

    public void move(int dx, int dy, int panelWidth, int panelHeight, int r) {

        // Sprawdzanie pozycji czołgu
        int NX = x + dx;
        int NY = y + dy;

        if (NX - SIZE / 2 >= 0 && NX + SIZE / 2 <= panelWidth && NY - SIZE / 2 >= 0 && NY + SIZE / 2 <= panelHeight) {
            x = NX;
            y = NY;
        }

        this.angle += r;

        System.out.println("x:" + x);
        System.out.println("y:" + y);
        System.out.println("r:" + angle);
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
