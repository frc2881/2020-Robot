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
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Robot;
/**
 *
 */
public class DriveForDistance extends Command {

    private double distance;
    private PIDController straightPID;
    //using the Ziegler-Nichols PID Control Tuning method, we find the proper numbers for the PID loop.
    private static final double Kc = 0.08;
    private static final double Pc = 0.291666;  // period of oscillation (found from average devided by 1/8 of a second(slow mo' camera))
    private static final double P = 0.08;//0.6 * Kc; 
    private static final double I = 0;//2 * P * 0.05 / Pc;
    private static final double D = 0;//0.125 * P * Pc / 0.05;
    private static double setpoint;

    public DriveForDistance(double distance) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        requires(Robot.drive);
        this.distance = distance;
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.log("Autonomous driving " + distance + " ft: " + Robot.drive.getLocation());
        setpoint = distance + Robot.drive.getDriveEncoderDistance();
        straightPID = new PIDController(Robot.oi.getStraightPIDValue(), I * 0.1, D * 0.1);
        straightPID.setSetpoint(setpoint);
        straightPID.setTolerance(.5);
        straightPID.enableContinuousInput(-1, 1);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double value = straightPID.calculate(setpoint - Robot.drive.getDriveEncoderDistance());
       // Sets the minimum and maximum speed of the robot during the command 
       if (value > 0.5) {
           value = 0.5;
       } else if (value < -0.5) {
           value = -0.5;
       }
        Robot.drive.tankDrive(value, value);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        //asking the PID loop have we reached our position
        return straightPID.atSetpoint();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        Robot.drive.tankDrive(0, 0);
        Robot.logEnd(this);
        end();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.drive.tankDrive(0, 0);
        Robot.log("Drive Forward has ended: " + Robot.drive.getLocation());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Drive Forward Distance", () -> distance, (distance) -> this.distance = distance);
    }
}