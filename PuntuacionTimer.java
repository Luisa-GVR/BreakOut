import javax.swing.*;
import java.awt.*;

public class PuntuacionTimer extends javax.swing.JFrame {
    private PTBoard ptBoard;
    private int puntuacion;

private JLabel puntuacionLabel;
    public PuntuacionTimer(PTBoard ptBoard, int puntuacion) {
        initComponents();
        this.ptBoard = ptBoard;
        puntuacionLabel = new JLabel("Puntuación: 0");
        puntuacionLabel.setHorizontalAlignment(JLabel.CENTER);
        puntuacionLabel.setVerticalAlignment(JLabel.CENTER);
        Font font = new Font("Arial", Font.BOLD,30);
        puntuacionLabel.setFont(font);
        JPanel p = new JPanel(new BorderLayout());
        p.add(ptBoard);
        p.add(puntuacionLabel,BorderLayout.SOUTH);
        setContentPane(p);

    }
    public void actualizarPuntuacion(int puntuacion){
    puntuacionLabel.setText("Puntuación: " + puntuacion);
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("PuntuacionTimer"); // NOI18N
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }

}
