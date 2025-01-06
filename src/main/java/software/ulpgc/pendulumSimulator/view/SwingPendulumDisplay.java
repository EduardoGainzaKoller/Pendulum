package software.ulpgc.pendulumSimulator.view;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.util.function.Consumer;

public class SwingPendulumDisplay extends JPanel {
    private final int originX = 400;
    private final int originY = 100;
    private double pendulumX;
    private double pendulumY;

    private Consumer<Double> onAngleChange;
    private Consumer<Double> onLengthChange;
    private Consumer<Double> onDampingChange;

    public SwingPendulumDisplay() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateAngleFromMouse(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateAngleFromMouse(e.getX(), e.getY());
            }
        });
    }

    public void updatePendulumPosition(double x, double y) {
        this.pendulumX = x;
        this.pendulumY = y;
        repaint();
    }

    public void setOnAngleChangeListener(Consumer<Double> onAngleChange) {
        this.onAngleChange = onAngleChange;
    }

    public void setOnLengthChangeListener(Consumer<Double> onLengthChange) {
        this.onLengthChange = onLengthChange;
    }

    public void setOnDampingChangeListener(Consumer<Double> onDampingChange) {
        this.onDampingChange = onDampingChange;
    }

    private void updateAngleFromMouse(int mouseX, int mouseY) {
        double dx = mouseX - originX;
        double dy = mouseY - originY;
        double angle = Math.atan2(dx, dy);
        if (onAngleChange != null) {
            onAngleChange.accept(angle);
        }
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


    public JPanel createControls() {
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(3, 2));


        JLabel lengthLabel = new JLabel("Longitud:");
        JSlider lengthSlider = new JSlider(1, 300, 100);
        lengthSlider.addChangeListener(e -> {
            double newLength = lengthSlider.getValue() / 100.0;
            if (onLengthChange != null) {
                onLengthChange.accept(newLength);
            }
        });


        JLabel dampingLabel = new JLabel("Amortiguamiento:");
        JSlider dampingSlider = new JSlider(0, 5, 1);
        dampingSlider.addChangeListener(e -> {
            double newDamping = dampingSlider.getValue() / 10.0;
            if (onDampingChange != null) {
                onDampingChange.accept(newDamping);
            }
        });


        controls.add(lengthLabel);
        controls.add(lengthSlider);
        controls.add(dampingLabel);
        controls.add(dampingSlider);

        return controls;
    }
}


