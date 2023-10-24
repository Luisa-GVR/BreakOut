import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Platform implements Runnable {

    private Rectangle2D.Double platform;

    private int platformX;

    public int getPlatformX() {
        return platformX;
    }


    private int platformY;
    private boolean pausado;

    public void pausar() {
        pausado = !pausado;
    }

    int velocidad = 10;

    public static final int ancho = 100;
    public static final int alto = 20;

    public Platform(Shape shape) {
        platform = (Rectangle2D.Double) shape;
        platformX = 400;
        platformY = 550;
        platform.x = platformX;
        platform.y = platformY;

    }

    public void moverDerecha(){
        platformX += velocidad;

    }

    public void moverIzquierda(){
        platformX -= velocidad;

    }

    @Override
    public void run() {

        while (true) {
            if (!pausado) {
                platform.x = platformX;
            }

            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}


