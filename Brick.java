import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Brick{

    int brickX;
    int brickY;
    final int largo = 50;
    final int altura = 25;
    boolean roto;
    Color color;

    public Color getColor() {
        return color;
    }

    public int getBrickX() {
        return brickX;
    }

    public int getBrickY() {
        return brickY;
    }

    public int getLargo() {
        return largo;
    }

    public int getAltura() {
        return altura;
    }

    public Brick(int brickX, int brickY, boolean roto, Color color) {
        this.brickX = brickX;
        this.brickY = brickY;
        this.roto = roto;
        this.color = color;
    }


    public void setRoto(boolean roto) {
        this.roto = roto;

    }

    public boolean isRoto() {
        return roto;
    }

    public Rectangle2D getBounds2D() {
        return new Rectangle2D.Double(brickX, brickY, largo, altura);
    }


}
