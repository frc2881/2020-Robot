// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.commands.scoring.flywheel;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class ControlFlywheel extends Command {

    private double speed = 0;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public ControlFlywheel() {
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        requires(Robot.flywheel);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.logInitialize(this);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
            if (Robot.lift.readyForLift() || Robot.flywheel.getFlywheelStopped()) {
                speed = 0;
            } else if (Robot.flywheel.isFlywheelFullSpeed()) {
                speed = 0.85;
            } else {
                speed = 0.45;
            }

            Robot.flywheel.setFlywheel(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.logEnd(this);
        Robot.flywheel.setFlywheel(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        Robot.logInterrupted(this);
        Robot.flywheel.setFlywheel(0);
    }
}
