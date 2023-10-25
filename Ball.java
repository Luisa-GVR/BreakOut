import javax.swing.*;
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


    public static final int MAX_X = 800;
    public static final int MAX_Y = 600;



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


    public void setxPlataforma(int xPlataforma) {
        this.xPlataforma = xPlataforma;
    }

    boolean gameOver = false;

    public int getBallY() {
        return ballY;
    }

    ArrayList<Brick> ladrillos;

    public ArrayList<Brick> getLadrillos() {
        return ladrillos;
    }

    public void setLadrillos(ArrayList<Brick> ladrillos) {
        this.ladrillos = ladrillos;
    }

    int ladrilloCounter = 0;

    public int getLadrilloCounter() {
        return ladrilloCounter;
    }

    public void setLadrilloCounter(int ladrilloCounter) {
        this.ladrilloCounter = ladrilloCounter;
    }
    public void reiniciar(){
        ballX = 320;
        ballY = 240;
    }

    @Override
    public void run() {

        int sY = 1;
        int sX = 1;


        while(!gameOver){

            if(!pausado){

                if( ballX <= 0 ) {
                    if (sX == 0){
                        sX = -1;
                    }
                    sX = sX * SIGN;
                }

                if( ballX >= (MAX_X - 40) ) {
                    if (sX == 0){
                        sX = 1;
                    }
                    sX = sX * SIGN;
                }

                if (ballY > 520 && ballY < 538){

                    if (ballX >= xPlataforma -20 && ballX <= (xPlataforma+110)){
                        setPuntuacionPlataforma(getPuntuacionPlataforma() +1);
                        sY = -sY;

                        if (ballX >= xPlataforma-20 && ballX <= xPlataforma+29){ //izquierda, bota izquierda
                            sX = -1;
                        }

                        if (ballX >= xPlataforma+30 && ballX <= xPlataforma+60){  //centro, directo
                            sX = 0;
                        }

                        if (ballX >= xPlataforma+61 && ballX <= xPlataforma+110){ //derecha, bota derecha
                            sX = 1;
                        }
                    }
                }

                for(Brick brick : ladrillos){
                    if (!brick.isRoto()){
                        if (ball.getBounds2D().intersects(brick.getBounds2D())){
                            setLadrilloCounter(getLadrilloCounter() + 1);

                            brick.setRoto(true);
                            setPuntuacionPlataforma(getPuntuacionPlataforma() +1);
                            sY = sY * SIGN;


                            //los bricks son 50x25
                            if (ballX >= brick.getBrickX()  && ballX <= brick.getBrickX() + 15 ){ //izquierda, bota izquierda
                                sX = sX * -1;
                            }

                            if (ballX >= brick.getBrickX()+16 && ballX <=brick.getBrickX() +34 ){  //centro, directo
                                sX = 0;
                            }

                            if (ballX >= brick.getBrickX()+35  && ballX <= brick.getBrickX()+50 ){ //derecha, bota derecha
                                sX = sX;
                            }



                        }
                    }
                }

                if( ballY <= 0 ) {
                    sY = sY * SIGN;
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
