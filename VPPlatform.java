import javax.swing.*;
import java.awt.*;

public class VPPlatform extends JComponent {

    Platform platform;

    public VPPlatform(Platform platform) {
        this.platform = platform;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        platform.paint(g);
    }
}