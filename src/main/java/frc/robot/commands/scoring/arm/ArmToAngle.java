// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.commands.scoring.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class ArmToAngle extends Command {
    private double height;

    public ArmToAngle(double angle) {
        requires(Robot.arm);
        height = Robot.arm.toHeightInches(angle);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.logInitialize(this);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        // Calls to the subsystem to update the angle if controller value has changed
        double time = timeSinceInitialized();
        double speed;
        double difference = height - Robot.arm.getArmPosition();
        double multiplier = difference > 0 ? 0.6 : 0.35;
        // Sets the minimum and maximum speed of the robot during the command
        

        if (Math.abs(difference) <= 0.15) {
            speed = 0;
        } else if (time < 1) {
            speed = Math.copySign(time * multiplier + 0.05, difference);
        } else if (Math.abs(difference) < 3) {
            speed = difference * multiplier;
        } else if (Math.abs(difference) >= 3) {
            speed = Math.copySign(multiplier, difference);
        } else {
            speed = 0;
        }

        Robot.log("remaining distance: " + difference);
        Robot.log("speed: " + speed);
        Robot.arm.setArmSpeed(-speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        // asking the pid loop have we reached our position
        return Math.abs(height - Robot.arm.getArmPosition()) < 0.1;
    }

    @Override
    protected void interrupted() {
        Robot.logInterrupted(this);
        // call the drive subsystem to make sure the PID loop is disabled
        Robot.arm.setArmSpeed(0);
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.logEnd(this);
        // call the drive subsystem to make sure the PID loop is disabled
        Robot.arm.setArmSpeed(0);
    }

}
