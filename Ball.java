import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

public class Ball implements Runnable{

    private Ellipse2D.Double ball;

    public static final int SIGN = -1;
    private int ballX;
    private int ballY;

    private boolean pausado;


    public void pausar() {
        pausado = !pausado;
    }

    int velocidad = 10;

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad + 1;
    }

    public int getVelocidad() {
        return velocidad;
    }



//borrar estos???, son prueba, dejaran de funcionar cuando pongamos los ladrillos XD

    public static final int MAX_X = 800;
    public static final int MAX_Y = 600;
    public static final int DX = 10;
    public static final int DY = 10;



    public Ball(Shape shape, ArrayList<Brick> ladrillos) {
        ball = (Ellipse2D.Double) shape;
        ballX = 320;
        ballY = 240;
        ball.x = ballX;
        ball.y = ballY;
        this.ladrillos = ladrillos;
    }

    int xPlataforma;

    int puntuacionPlataforma = 0;

    public int getPuntuacionPlataforma() {
        return puntuacionPlataforma;
    }

    public void setPuntuacionPlataforma(int puntuacionPlataforma) {
        this.puntuacionPlataforma = puntuacionPlataforma;
    }

    public int getxPlataforma() {
        return xPlataforma;
    }

    public void setxPlataforma(int xPlataforma) {
        this.xPlataforma = xPlataforma;
    }

    boolean gameOver = false;


    ArrayList<Brick> ladrillos;

    public ArrayList<Brick> getLadrillos() {
        return ladrillos;
    }

    public void setLadrillos(ArrayList<Brick> ladrillos) {
        this.ladrillos = ladrillos;
    }

    public Ball(ArrayList<Brick> ladrillos) {
        this.ladrillos = ladrillos;
    }

    @Override
    public void run() {
        Random random = new Random();
        int randomNumber = (int) ((random.nextDouble() * 2) - 1);

        int sY = 1;
        int sX = 1;


        while(!gameOver){

            if(!pausado){

                if( ballX < 0 ) {
                    sX = (sX * SIGN) + randomNumber;
                }

                if( ballX > (MAX_X - 40) ) {
                    sX = (sX * SIGN) + randomNumber;
                }

                if (ballY > 520 && ballY < 532){
                    if (ballX >= xPlataforma -20 && ballX <= (xPlataforma+110)){
                        setPuntuacionPlataforma(getPuntuacionPlataforma() +1);

                        sY = (sY * SIGN) + randomNumber;
                    }
                }

                for(Brick brick : ladrillos){
                    if (!brick.isRoto()){
                        if (ball.getBounds2D().intersects(brick.getBounds2D())){
                            brick.setRoto(true);
                            setPuntuacionPlataforma(getPuntuacionPlataforma() +1);

                            sX = (sX * SIGN) + randomNumber;
                            sY = (sY * SIGN) + randomNumber;
                        }
                    }
                }

                if( ballY < 0 ) {
                    sY = (sY * SIGN) + randomNumber;
                }

                if( ballY > (MAX_Y - 55) ) {
                    System.out.println("perder");
                    pausar();
                }

                ballX = ballX + (velocidad * sX) ;
                ballY = ballY + (velocidad * sY);
                ball.x = ballX ;
                ball.y = ballY;
            }

            try {
                Thread.sleep(80L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
