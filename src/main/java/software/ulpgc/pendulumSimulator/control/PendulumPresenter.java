package software.ulpgc.pendulumSimulator.control;

import software.ulpgc.pendulumSimulator.Pendulum;
import software.ulpgc.pendulumSimulator.PendulumSimulator;
import software.ulpgc.pendulumSimulator.view.SwingPendulumDisplay;

import java.util.Timer;
import java.util.TimerTask;

public class PendulumPresenter {
    private final SwingPendulumDisplay view;
    private final PendulumSimulator simulator;
    private Pendulum pendulum;
    private final static int period = (int) (1000 * 0.001);

    public PendulumPresenter(SwingPendulumDisplay view, PendulumSimulator simulator, Pendulum pendulum) {
        this.view = view;
        this.simulator = simulator;
        this.pendulum = pendulum;


        view.setOnAngleChangeListener(this::onAngleChanged);
    }

    public void execute() {
        new Timer().schedule(task(), 0, period);
    }

    private TimerTask task() {
        return new TimerTask() {
            @Override
            public void run() {
                updateView();
            }
        };
    }

    private void updateView() {
        pendulum = simulator.simulate(pendulum);
        double pendulumX = 400 + pendulum.L() * 200 * Math.sin(pendulum.theta());
        double pendulumY = 100 + pendulum.L() * 200 * Math.cos(pendulum.theta());
        view.updatePendulumPosition(pendulumX, pendulumY);
    }

    public void onAngleChanged(double newAngle) {
        pendulum = new Pendulum(
                pendulum.L(),
                pendulum.b(),
                pendulum.g(),
                newAngle,
                0
        );
        updateView();
    }

    public void updateLength(double newLength) {
        pendulum = new Pendulum(
                newLength,
                pendulum.b(),
                pendulum.g(),
                pendulum.theta(),
                pendulum.omega()
        );
        updateView();
    }

    public void updateDamping(double newDamping) {
        pendulum = new Pendulum(
                pendulum.L(),
                newDamping,
                pendulum.g(),
                pendulum.theta(),
                pendulum.omega()
        );
        updateView();
    }
}
