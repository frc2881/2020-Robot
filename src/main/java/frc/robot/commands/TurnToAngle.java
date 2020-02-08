// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.Robot;

/**
 *
 */
public class TurnToAngle extends Command {
    private double angle;
    private PIDController turnPID;
    //using the Ziegler-Nichols PID Control Tuning method, we find the proper numbers for the PID loop.
    private static final double Kc = 0.08;
    private static final double Pc = 0.291666;  // period of oscillation (found from average devided by 1/8 of a second(slow mo' camera))
    private static final double P = 0.6 * Kc; 
    private static final double I = 2 * P * 0.05 / Pc;
    private static final double D = 0.125 * P * Pc / 0.05;

    public TurnToAngle(double angle) {
        requires(Robot.drive);
        this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        /* Make a call to the subsystem to use a PID loop controller in the subsystem
        to set the heading based on the HAT controller. */
        turnPID = new PIDController(P, I * 0.1, D * 0.1); //<-- tuned from testing
        turnPID.setSetpoint(angle);
        turnPID.setTolerance(.5);
        turnPID.enableContinuousInput(-180, 180);

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        // Calls to the subsystem to update the angle if controller value has changed
        // Robot.drive.autonomousRotate(rotateToAngleRate, -rotateToAngleRate);
        double value = turnPID.calculate(Robot.navX.getYaw());
       // Sets the minimum and maximum speed of the robot during the command 
       if (value > 0.5) {
           value = 0.5;
       } else if (value < -0.5) {
           value = -0.5;
       }
        Robot.drive.tankDrive(value, -value);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        // asking the pid loop have we reached our position
        return turnPID.atSetpoint();
    }

    @Override
    protected void interrupted() {
        // call the drive subsystem to make sure the PID loop is disabled
        Robot.drive.tankDrive(0, 0);
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        // call the drive subsystem to make sure the PID loop is disabled
        Robot.drive.tankDrive(0, 0);
    }

}
