import java.awt.*;
import java.awt.geom.Ellipse2D;

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



    public Ball(Shape shape) {
        ball = ( Ellipse2D.Double) shape;
        ballX = 320;
        ballY = 240;
        ball.x = ballX ;
        ball.y = ballY;
    }

    @Override
    public void run() {

        int sY = 1;
        int sX = 1;

        while(true){

            if(!pausado){
                if( ballY < 0 ) {
                    sY = sY * SIGN;
                }

                if( ballX < 0 ) {
                    sX = sX * SIGN;
                }
                if( ballY > (MAX_Y - 55) ) {
                    sY = sY * SIGN;
                }

                if( ballX > (MAX_X - 40) ) {
                    sX = sX * SIGN;
                }

                ballX = ballX + (velocidad * sX) ;
                ballY = ballY + (velocidad * sY);
                ball.x = ballX ;
                ball.y = ballY;
            }



            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
