import javax.swing.*;
import java.awt.*;

public class PuntuacionTimer extends javax.swing.JFrame {

    public PuntuacionTimer(PTBoard ptBoard) {
        initComponents();
        JPanel p = new JPanel(new BorderLayout());

        p.add(ptBoard);
        setContentPane(p);
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
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }

}
