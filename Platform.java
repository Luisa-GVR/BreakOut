import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Platform {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 20;
    private static final int SPEED = 5;

    private int platformX;
    private int platformY;
    private boolean movingLeft;
    private boolean movingRight;

    public Platform() {
        platformX = (VentanaPrincipal.WIDTH - WIDTH) / 2;
        platformY = VentanaPrincipal.HEIGHT - HEIGHT - 20;
    }

    public void moveLeft() {
        movingLeft = true;
        movingRight = false;
    }

    public void moveRight() {
        movingLeft = false;
        movingRight = true;
    }

    public void stop() {
        movingLeft = false;
        movingRight = false;
    }

    public void update() {
        if (movingLeft && platformX > 0) {
            platformX -= SPEED;
        } else if (movingRight && platformX < VentanaPrincipal.WIDTH - WIDTH) {
            platformX += SPEED;
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fill(new Rectangle2D.Double(platformX, platformY, WIDTH, HEIGHT));
    }
}
