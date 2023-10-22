import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class VentanaPrincipal extends javax.swing.JFrame {
    PTBoard ptBoard;
    PuntuacionTimer sv2;
    VPBoard vpBoard;

    public VentanaPrincipal() {
        initComponents();


        setLocationRelativeTo(null);

        vpBoard = new VPBoard();

        JPanel p = new JPanel(new BorderLayout());

        p.add(vpBoard, BorderLayout.CENTER);
        setContentPane(p);



        //Abrir ventana 2
        ptBoard = new PTBoard();
        sv2 = new PuntuacionTimer(ptBoard);


        int x = getX() - sv2.getWidth();
        int y = getY() + (getHeight() - sv2.getHeight()) / 2;

        sv2.setLocation(x,y);
        sv2.setVisible(true);
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("VentanaPrincipal"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
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
    }// </editor-fold>

    boolean pause = true;

    private void formKeyTyped(java.awt.event.KeyEvent evt) {


        if (evt.getKeyChar() == '+') {
            System.out.println("+:");
        } else if (evt.getKeyChar() == '-') {
            System.out.println("-:");

        }


        //pausar
        if (evt.getKeyChar() == 'p'){

            if (pause){
                ptBoard.setPausado(false);
                ptBoard.pausar();
                vpBoard.pausarBall();
            }

            pause = false;
        } else if (evt.getKeyChar() == ' '){
            if (!pause){
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
