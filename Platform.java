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
        movingRight = true;
        movingLeft = false;
    }

    public void stopMoving() {
        movingLeft = false;
        movingRight = false;
    }

    public void run() {
        while (true) {
            movePlatform();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void movePlatform() {
        if (movingLeft && platformX > 0) {
            platformX -= SPEED;
        }
        if (movingRight && platformX < VentanaPrincipal.WIDTH - WIDTH) {
            platformX += SPEED;
        }
    }

}
