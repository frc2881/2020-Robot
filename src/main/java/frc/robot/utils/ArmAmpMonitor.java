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
    private double previous;
    private long violations;
    private boolean armGoingUp;

    public ArmAmpMonitor(double upThreshold, double downThreshold, DoubleSupplier currentSupplier, DoubleSupplier direction) {
        this.upThreshold = upThreshold;
        this.downThreshold = downThreshold;
        this.currentSupplier = currentSupplier;
        this.direction = direction;
    }

    public void reset() {
        previous = Double.MAX_VALUE;
        violations = 0;
    }

    public boolean checkTriggered() {
        // Is current above threshold and not dropping?  (ie. is motor stopped & not accelerating?)
        double current = currentSupplier.getAsDouble();
        double velocity = direction.getAsDouble();
        if (velocity < 0) {
            if (current >= downThreshold && current >= previous) {
                violations++;
            }
            previous = current;
            armGoingUp = false;
        } else {
            if (current >= upThreshold && current >= previous) {
                violations++;
            }
            previous = current;
            armGoingUp = true;
        }

        return isTriggered();
    }

    public boolean isTriggered() {
        // Require at least 2 violations to get past noise due to initial conditions
        return violations >= 2;
    }

    public boolean armGoingUp() {
        return armGoingUp;
    }
}