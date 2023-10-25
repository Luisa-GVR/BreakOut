import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class VPBoard extends JComponent implements Runnable{

    Thread ballAnimator;

    Thread refresher;
    Ellipse2D.Double ballObj;
    Ball ball;



    ArrayList<Brick> ladrillos;

    public ArrayList<Brick> getLadrillos() {
        return ball.getLadrillos();
    }

    public void setLadrillos(ArrayList<Brick> ladrillos) {
        this.ladrillos = ladrillos;
        ball.setLadrillos(ladrillos);
    }

    public void reiniciar() {
        ballObj.setFrame(20, 320, 20, 20);
        ball.reiniciar();
        velocidad = 10;
    }

    int velocidad = 10;

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    int xPlataforma;

    public int getxPlataforma() {
        return xPlataforma;
    }

    public void setxPlataforma(int xPlataforma) {
        this.xPlataforma = xPlataforma;
        ball.setxPlataforma(xPlataforma);
    }

    int puntuacionPlataformaBoard;

    public int getPuntuacionPlataformaBoard() {
        return ball.getPuntuacionPlataforma();
    }

    public void setPuntuacionPlataformaBoard(int puntuacionPlataformaBoard) {
        this.puntuacionPlataformaBoard = puntuacionPlataformaBoard;
    }



    public VPBoard(ArrayList<Brick> ladrillos) {
        ballObj = new Ellipse2D.Double(20, 320, 20, 20);
        ball = new Ball(ballObj, ladrillos);
        ballAnimator = new Thread(ball);
        ballAnimator.start();
        refresher = new Thread(this);
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

    public void pausarBall() {
        if (ball != null) {
            ball.pausar();
        }
    }

    public void decreaseBallSpeed() {
        ball.setVelocidad(velocidad--);
    }

    public void increaseBallSpeed() {
        ball.setVelocidad(velocidad++);

    }
}
