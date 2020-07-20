package frc.robot.utils;

import java.util.function.DoubleSupplier;

/**
 * Helper for monitoring for conditions where motors suck too much current, usually because the motor isn't turning.
 */
public class ArmAmpMonitor {
    private final double upThreshold;
    private final double downThreshold;
    private final DoubleSupplier currentSupplier;
    private final DoubleSupplier direction;
    private final double coefficient = 0.3;
    private double previous;
    private double estimated;
    private boolean armGoingUp;

    public ArmAmpMonitor(double upThreshold, double downThreshold, DoubleSupplier currentSupplier, DoubleSupplier direction) {
        this.upThreshold = upThreshold;
        this.downThreshold = downThreshold;
        this.currentSupplier = currentSupplier;
        this.direction = direction;
    }

    public void reset() {
        previous = 0;
    }

    public boolean checkTriggered() {
        // Is current above threshold and not dropping?  (ie. is motor stopped & not accelerating?)
        double current = currentSupplier.getAsDouble();
        double velocity = direction.getAsDouble();
        
        estimated = previous * coefficient + current * (1 - coefficient);
        previous = current;
        armGoingUp = velocity > 0;

        return isTriggered();
    }

    public boolean isTriggered() {
        // Require at least 2 violations to get past noise due to initial conditions
        boolean triggered;
        if (armGoingUp) {
            triggered = (estimated >= upThreshold);
        } else {
            triggered = (estimated >= downThreshold);
        }
        return triggered;
    }

    public double current() {
        return currentSupplier.getAsDouble();
    }

    public double estimated() {
        return estimated;
    }

    public boolean armGoingUp() {
        return armGoingUp;
    }
}