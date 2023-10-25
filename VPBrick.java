import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class VPBrick extends JComponent {

    int filasBricks = 5;
    int columnaBricks = 16;

    int tamanioLongitud = (800/columnaBricks) + 1;

    private ArrayList<Brick> ladrillos;

    public ArrayList<Brick> getLadrillos() {
        return ladrillos;
    }


    public VPBrick() {
        ladrillos = new ArrayList<>();

        for (int fila = 0; fila < filasBricks; fila++) {
            for (int columna = 0; columna < columnaBricks; columna++) {
                int brickX = columna * tamanioLongitud;
                int brickY = fila * 25;
                Brick brick = new Brick(brickX, brickY, false, getColorPorIndice(fila));
                ladrillos.add(brick);
            }
        }
    }
    public void reiniciar(){
        for (Brick brick : ladrillos){
            brick.setRoto(false);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Brick brick : ladrillos) {
            if (!brick.isRoto()) {
                Stroke borde = new BasicStroke(2);
                g2.setStroke(borde);
                g.setColor(brick.getColor());
                g2.fill(new Rectangle2D.Double(brick.getBrickX(), brick.getBrickY(), brick.getLargo(), brick.getAltura()));
                g.setColor(Color.BLACK);
                g2.draw(new Rectangle2D.Double(brick.getBrickX(), brick.getBrickY(), brick.getLargo(), brick.getAltura()));
            }
        }
    }

    private Color getColorPorIndice(int index) {
        switch (index) {
            case 0:
                return Color.RED;
            case 1:
                return Color.ORANGE;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.BLUE;
            default:
                return Color.WHITE;
        }
    }
}
