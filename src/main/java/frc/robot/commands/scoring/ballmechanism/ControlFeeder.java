// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.commands.scoring.ballmechanism;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class ControlFeeder extends Command {
    /*
     * - rotate intake wheels (4?) - 1 motor, inverted directions - rotate
     * perpendicular bands (3) - 2 motors, 2 directions - rotate parallel bands (2)
     * - 1 motor, inverted directions
     */
    // change to more appropriate speed

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public ControlFeeder() {
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        requires(Robot.intake);
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

        double speed;
        // made a deadband with a boolean because the spark maxs dont have a function
        // for one
        if (Robot.intake.feederStop || Robot.lift.readyForLift() || Robot.arm.getArmAngle() > 18) {
            speed = 0;
        } else if (Robot.intake.intakeFeeder) {
            speed = -1;
        } else {
            speed = 0.5;
        }

        Robot.intake.controlFeeder(speed);

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
        Robot.intake.controlFeeder(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        Robot.logInterrupted(this);
        Robot.intake.controlFeeder(0);
    }
}
