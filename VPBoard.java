import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class VPBoard extends JComponent implements Runnable{

    Thread ballAnimator;

    Thread refresher;
    Ellipse2D.Double ballObj;


    public VPBoard(){

        ballObj = new Ellipse2D.Double(20,320,20,20);
        Ball ball = new Ball(ballObj);
        ballAnimator = new Thread(ball);
        ballAnimator.start();
        refresher= new Thread( this);
        refresher.start();
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

        g2.setColor(Color.RED);
        g2.fill(ballObj);
        g2.setColor(Color.BLACK);
    }


    @Override
    public void run() {
        while ( true ) {
            repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
