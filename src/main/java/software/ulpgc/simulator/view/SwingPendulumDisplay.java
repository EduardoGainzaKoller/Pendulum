package software.ulpgc.simulator.view;

import javax.swing.*;
import java.awt.*;

public class SwingPendulumDisplay extends JPanel {
    private final int originX = 400;
    private final int originY = 100;
    private double pendulumX;
    private double pendulumY;

    public SwingPendulumDisplay() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
    }

    public void updatePendulumPosition(double x, double y) {
        this.pendulumX = x;
        this.pendulumY = y;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(originX, originY, (int) pendulumX, (int) pendulumY);

        g2d.setColor(Color.RED);
        g2d.fillOval((int) pendulumX - 10, (int) pendulumY - 10, 20, 20);

        g2d.setColor(Color.BLUE);
        g2d.fillOval(originX - 5, originY - 5, 10, 10);
    }
}
