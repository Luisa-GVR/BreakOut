import javax.swing.*;

public class VPPlatform extends JComponent implements Runnable {
    private Platform platform;

    public VPPlatform(Platform platform) {
        this.platform = platform;
    }


    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}