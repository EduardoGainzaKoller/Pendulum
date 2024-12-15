package software.ulpgc.simulator;

import software.ulpgc.simulator.view.PendulumPresenter;
import software.ulpgc.simulator.view.SwingPendulumDisplay;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // If you want to see a graph of the pendulum's motion, uncomment this section of the code.
        /*
        Pendulum pendulum = new Pendulum(2, 1, -9.8, Math.PI/4, 0.0);

        PendulumSimulator pendulumSimulator = new PendulumSimulator(0.001);

        PendulumData data = new PendulumData();

        double time = 0;
        double finalTime = 30;
        double dt = 0.001;

        while(time<finalTime) {
            data.addData(time, pendulum.theta(), pendulum.omega());
            pendulum = pendulumSimulator.simulate(pendulum);
            time+= dt;
        }

        PendulumGraph.plotPendulumData(data);
         */


        Pendulum pendulum = new Pendulum(2, 1, -9.8, Math.PI/4, 0.0);
        PendulumSimulator simulator = new PendulumSimulator(0.001);
        SwingPendulumDisplay view = new SwingPendulumDisplay();

        PendulumPresenter presenter = new PendulumPresenter(view, simulator, pendulum);

        JFrame frame = new JFrame("Pendulum Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        presenter.execute();
    }
}
