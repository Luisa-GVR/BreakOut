import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class VentanaPrincipal extends javax.swing.JFrame implements ActionListener {
    PTBoard ptBoard;
    PuntuacionTimer sv2;
    VPBoard vpBoard;
    VPPlatform vpPlatform;
    VPBrick vpBrick;
    JPanel panel = null;
    JPanel panelV = null;

    private Ball ball;


    int puntuacionPlataforma = 0;




    private Timer updateTimer;





    public VentanaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);

        //ladrillos
        vpBrick = new VPBrick();
        vpBrick.setBounds(0, 0, 800, 400);

        //pelota
        vpBoard = new VPBoard(vpBrick.getLadrillos());
        vpBoard.setBounds(0, 0, 800, 580);

        //plataforma
        vpPlatform = new VPPlatform();
        vpPlatform.setBounds(0,580,800,600);

        JPanel p = new JPanel(new BorderLayout());


        p.add(vpBoard, BorderLayout.CENTER);
        p.add(vpBrick, BorderLayout.CENTER);
        p.add(vpPlatform, BorderLayout.CENTER);


        setContentPane(p);


        //cronometro
        ptBoard = new PTBoard();


    // Abrir ventana 2
        sv2 = new PuntuacionTimer(ptBoard,0);
        int x = getX() - sv2.getWidth();
        int y = getY() + (getHeight() - sv2.getHeight()) / 2;
        sv2.setLocation(x, y);
        sv2.setVisible(true);

        panelV = new JPanel(new GridLayout(1,1));
        JLabel velocidad = new JLabel("Ball Speed: " + vpBoard.getVelocidad());
        velocidad.setHorizontalAlignment(JLabel.RIGHT);
        velocidad.setFont(new Font("Arial",Font.BOLD,16));
        velocidad.setForeground(Color.RED);
        panelV.add(velocidad);

        //update constante
        updateTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ptBoard.setPuntuacionTimer(vpBoard.getPuntuacionPlataformaBoard());
                ptBoard.setPuntuacionTimer(vpBoard.getPuntuacionPlataformaBoard());
                vpBoard.setxPlataforma(vpPlatform.platform.getPlatformX());
                vpBoard.setLadrillos(vpBrick.getLadrillos());
                //AGREGAR CONTADOR DE VELOCIDAD :)
                velocidad.setText("Ball Speed: " + vpBoard.getVelocidad());
                velocidad.setVerticalAlignment(JLabel.BOTTOM);
                getContentPane().add(panelV);
                revalidate();
                repaint();

                if ((vpBoard.ball.getLadrilloCounter()) == 80){
                    System.out.println("ganaste");
                }


                // vpBoard.ball.getBallY() obtiene Y, 540 lose
                if ((vpBoard.ball.getBallY() > 540)){


                    ptBoard.setPausado(false);
                    ptBoard.pausar();
                    vpBoard.pausarBall();


                            panel = new JPanel(new GridLayout(2,1));
                            Border borde = BorderFactory.createLineBorder(Color.BLACK,20 );
                            panel.setBorder(borde);

                            JLabel pausadoLabel = new JLabel("G A M E  O V E R");
                            pausadoLabel.setHorizontalAlignment(JLabel.CENTER);
                            pausadoLabel.setVerticalAlignment(JLabel.BOTTOM);
                            pausadoLabel.setFont(new Font("Arial", Font.BOLD, 30));
                            pausadoLabel.setForeground(Color.RED);


                            JLabel pausaMessage = new JLabel ("");
                            pausaMessage.setHorizontalAlignment(JLabel.CENTER);
                            pausaMessage.setVerticalAlignment(JLabel.TOP);
                            pausaMessage.setFont(new Font("Arial",Font.BOLD,16));
                            pausaMessage.setForeground(Color.RED);

                            panel.add(pausadoLabel);
                            panel.add(pausaMessage);

                            JLayeredPane layeredPane = getLayeredPane();
                            layeredPane.add(panel, JLayeredPane.POPUP_LAYER);

                            panel.setBounds(0, 0, 786, 564);

                            revalidate();
                            repaint();

                    }
                sv2.actualizarPuntuacion(ptBoard.getPuntuacionTimer());
                }

        });
        updateTimer.start();





    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("VentanaPrincipal"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);


        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        pack();
    }

    //Aun no se utiliza reiniciarJuego por problemas tecnicos xd --------------------------------------------------------
    private void reiniciarJuego() {

        vpPlatform.reiniciar(); // Restablecer la posición de la plataforma
        vpBoard.reiniciar(); // Restablecer la velocidad de la pelota
        vpBrick.reiniciar(); // Restablecer los ladrillos

        if (panel != null) {
            JLayeredPane layeredPane = getLayeredPane();
            layeredPane.remove(panel);
            panel = null;
        }

        ptBoard.reiniciar();
        ptBoard.setPausado(true);
        vpBoard.reiniciar();

        revalidate();
        repaint();
    }

    boolean pause = true;

    @Override
    public void actionPerformed(ActionEvent e) {
        vpPlatform.repaint();
    }



    private void formKeyTyped(KeyEvent evt) {



        // Platform
        if (evt.getKeyChar() == 'd' || evt.getKeyChar() == 'D') {
            if (vpPlatform.platform.getPlatformX()<680){
                if (pause){
                    vpPlatform.moverDerecha();


                }
            }
        } else if (evt.getKeyChar() == 'a' || evt.getKeyChar() == 'A') {
            if (vpPlatform.platform.getPlatformX()>0) {
                if (pause){
                    vpPlatform.moverIzquierda();
                }
            }
        }

        //velocidad
        if (evt.getKeyChar() == '+') {

            if (vpBoard.getVelocidad() < 20){
                vpBoard.increaseBallSpeed();


            }


        } else if (evt.getKeyChar() == '-') {

            if (vpBoard.getVelocidad() > 10){
                vpBoard.decreaseBallSpeed();

            }

        }


        // Pausar
        if (evt.getKeyChar() == 'p' || evt.getKeyChar() == 'P'){

            if (pause){
                ptBoard.setPausado(false);
                ptBoard.pausar();
                vpBoard.pausarBall();

                if (panel != null){
                    JLayeredPane layeredPane = getLayeredPane();
                    layeredPane.remove(panel);
                    panel = null;
                }else{
                panel = new JPanel(new GridLayout(2,1));
                Border borde = BorderFactory.createLineBorder(Color.BLACK,20 );
                panel.setBorder(borde);

                JLabel pausadoLabel = new JLabel("P A U S E");
                pausadoLabel.setHorizontalAlignment(JLabel.CENTER);
                pausadoLabel.setVerticalAlignment(JLabel.BOTTOM);
                pausadoLabel.setFont(new Font("Arial", Font.BOLD, 30));
                pausadoLabel.setForeground(Color.RED);


                JLabel pausaMessage = new JLabel ("Press the 'Spacebar' to continue");
                pausaMessage.setHorizontalAlignment(JLabel.CENTER);
                pausaMessage.setVerticalAlignment(JLabel.TOP);
                pausaMessage.setFont(new Font("Arial",Font.BOLD,16));
                pausaMessage.setForeground(Color.RED);

                panel.add(pausadoLabel);
                panel.add(pausaMessage);

                JLayeredPane layeredPane = getLayeredPane();
                layeredPane.add(panel, JLayeredPane.POPUP_LAYER);

                panel.setBounds(0, 0, 786, 564);

                revalidate();
                repaint();
                }
            }

            pause = false;
        } else if (evt.getKeyChar() == ' '){
            if (!pause){
                ptBoard.setPausado(true);
                ptBoard.pausar();
                vpBoard.pausarBall();

                if (panel != null) {
                    JLayeredPane layeredPane = getLayeredPane();
                    layeredPane.remove(panel);
                    panel = null; // Marca el panel como null para indicar que ha sido eliminado
                }

                revalidate();
                repaint();
            }
            pause = true;
        }

    }



    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });

    }

}