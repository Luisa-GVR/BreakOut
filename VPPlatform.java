import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class VPPlatform extends JComponent implements Runnable {

    Thread platformAnimator;
    Thread refresher;
    Rectangle2D.Double platformObj;
    Platform platform;

    public VPPlatform(){

        platformObj = new Rectangle2D.Double(400,550,100,20);
        platform = new Platform(platformObj);
        platformAnimator = new Thread(platform);
        platformAnimator.start();
        refresher = new Thread(this);
        refresher.start();

    }

    public void moverIzquierda(){
        platform.moverIzquierda();
    }

    public void moverDerecha(){
        platform.moverDerecha();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isOpaque()) {
            g.setColor( getBackground() );
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(getForeground());
        }

        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(5.0f));

        g2.setColor(Color.BLACK);
        g2.fill(platformObj);
        g2.setColor(Color.BLACK);
    }
    public void reiniciar(){
        platformObj.setRect(400,550,100,20);
        platform.reiniciar();
    }



    @Override
    public void run() {
        while ( true ) {
            repaint();
            try {
                Thread.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void pausarBall() {
        if (platform != null) {
            platform.pausar();
        }
    }

}