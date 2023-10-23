import javax.swing.*;
import java.awt.*;

public class PTBoard extends JComponent implements Runnable {
    //timer

    private boolean pausado;
    private long startTime;
    private long elapsedTime;
    private Font largeFont;


    //Hola zazu, probablemente me estes leyendo, te recuerdo que la puntuacion
    // que debe de mostrar es puntuacionTimer, almenos por ahora
    // luego que terminemos bricks vemos que mas jeje
    //hola soy un comentario :3

    Thread refresher;
    Thread timeAnimator;

    int puntuacionTimer;

    public void setPuntuacionTimer(int puntuacionTimer) {
        this.puntuacionTimer = puntuacionTimer;
    }

    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }

    public PTBoard() { //Refresher...
        largeFont = new Font("Arial", Font.BOLD, 40);
        timeAnimator = new Thread();
        timeAnimator.start();
        refresher = new Thread(this);
        refresher.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(getForeground());
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(20.0f));
        g2.setColor(Color.BLACK);
        g2.setFont(largeFont);
        g2.drawString(formatElapsedTime(), 10, 30);
    }

    private String formatElapsedTime() {
        long minutes = (elapsedTime / 60000) % 60;
        long seconds = (elapsedTime / 1000) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        while (true) {
            if (!pausado) {
                long currentTime = System.currentTimeMillis();
                elapsedTime = currentTime - startTime;
                repaint();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void pausar() {
        pausado = !pausado;
        if (!pausado) {
            startTime = System.currentTimeMillis() - elapsedTime;
            System.out.println("resumido...");
        } else {
            System.out.println("pausado...");
        }
    }
}
