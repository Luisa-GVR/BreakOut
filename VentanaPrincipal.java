import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class VentanaPrincipal extends javax.swing.JFrame implements ActionListener {
    PTBoard ptBoard;
    PuntuacionTimer sv2;
    VPBoard vpBoard;
    VPPlatform vpPlatform;


    public VentanaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);

        JPanel p = new JPanel(new BorderLayout());

        //agregar pelota
        vpBoard = new VPBoard();
        p.add(vpBoard, BorderLayout.NORTH);

        //agregar plataforma

        vpPlatform = new VPPlatform();
        vpPlatform.setBounds(0,20,800,20);
        p.add(vpPlatform, BorderLayout.CENTER);



        setContentPane(p);

        //cronometro
        ptBoard = new PTBoard();



    // Abrir ventana 2
        sv2 = new PuntuacionTimer(ptBoard);
        int x = getX() - sv2.getWidth();
        int y = getY() + (getHeight() - sv2.getHeight()) / 2;
        sv2.setLocation(x, y);
        sv2.setVisible(true);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }

    boolean pause = true;

    @Override
    public void actionPerformed(ActionEvent e) {
        vpPlatform.repaint();
    }

    private void formKeyTyped(KeyEvent evt) {
        // Platform
        if (evt.getKeyChar() == 'd') {
            if (vpPlatform.platform.getPlatformX()<680){
                if (pause){
                    vpPlatform.moverDerecha();

                }
            }
        } else if (evt.getKeyChar() == 'a') {
            if (vpPlatform.platform.getPlatformX()>0) {
                if (pause){
                    vpPlatform.moverIzquierda();
                }
            }
        }

        //velocidad
        if (evt.getKeyChar() == '+') {

            if (vpBoard.getVelocidad() < 50){
                vpBoard.increaseBallSpeed();
                System.out.println(vpBoard.getVelocidad());
            }


        } else if (evt.getKeyChar() == '-') {

            if (vpBoard.getVelocidad() > 10){
                vpBoard.decreaseBallSpeed();
                System.out.println(vpBoard.getVelocidad());

            }

        }


        // Pausar
        if (evt.getKeyChar() == 'p') {
            if (pause) {
                ptBoard.setPausado(false);
                ptBoard.pausar();
                vpBoard.pausarBall();
            }
            pause = false;
        } else if (evt.getKeyChar() == ' ') {
            if (!pause) {
                ptBoard.setPausado(true);
                ptBoard.pausar();
                vpBoard.pausarBall();
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