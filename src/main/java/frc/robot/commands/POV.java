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
import frc.robot.Robot;

/**
 *
 */
public class POV extends Command {


    public POV() {
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        int angle = getDriverPOVAngle();
        //Robot.log("Turn to POV has started: " + angle);
        //Make a call to the subsystem to use a PID loop controller in the subsystem
        //to set the heading based on the HAT controller.
        Robot.drive.initializeTurnToHeading(angle);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        //Calls to the subsystem to update the angle if controller value has changed
        double rotateToAngleRate = Robot.drive.getRotateToAngleRate();
        //Robot.drive.autonomousRotate(rotateToAngleRate, -rotateToAngleRate);
        Robot.drive.changeHeadingTurnToHeading(getDriverPOVAngle());
    }

    //returns an integer angle based on what the driver controller reads
    private int getDriverPOVAngle() {
        int angle = Robot.oi.driver.getPOV();
        if (angle > 180) {
            angle = angle - 360;
        }
        return angle;
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        //asking the pid loop have we reached our position
        return Robot.drive.isFinishedTurnToHeading();
    }

    @Override
    protected void interrupted() {
        //call the drive subsystem to make sure the PID loop is disabled
        Robot.drive.endTurnToHeading();
        
        //Robot.log("Turn to POV was interrupted");
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        //call the drive subsystem to make sure the PID loop is disabled
        Robot.drive.endTurnToHeading();
        
        //Robot.log("Turn to POV has finished");
    }

}
