package software.ulpgc.pendulumSimulator;

import software.ulpgc.pendulumSimulator.DataView.PendulumData;
import software.ulpgc.pendulumSimulator.DataView.PendulumGraph;
import software.ulpgc.pendulumSimulator.control.PendulumPresenter;
import software.ulpgc.pendulumSimulator.view.SwingPendulumDisplay;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        

        Pendulum pendulumG = new Pendulum(2, 0.1, -9.8, Math.PI/4, 0.0);

        PendulumSimulator pendulumSimulator = new PendulumSimulator(0.001);

        PendulumData data = new PendulumData();

        double time = 0;
        double finalTime = 120;
        double dt = 0.001;

        while(time<finalTime) {
            data.addData(time, pendulumG.theta(), pendulumG.omega());
            pendulumG = pendulumSimulator.simulate(pendulumG);
            time+= dt;
        }

        PendulumGraph.plotPendulumData(data);



        SwingPendulumDisplay display = new SwingPendulumDisplay();
        Pendulum pendulum = new Pendulum(1.0, 0.1, -9.8, Math.PI / 4, 0.0);
        PendulumSimulator simulator = new PendulumSimulator(0.001);
        PendulumPresenter presenter = new PendulumPresenter(display, simulator, pendulum);

        JFrame frame = new JFrame("Péndulo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(display, BorderLayout.CENTER);
        frame.add(display.createControls(), BorderLayout.SOUTH);

        display.setOnAngleChangeListener(presenter::onAngleChanged);
        display.setOnLengthChangeListener(presenter::updateLength);
        display.setOnDampingChangeListener(presenter::updateDamping);

        frame.pack();
        frame.setVisible(true);

        presenter.execute();

    }
}
