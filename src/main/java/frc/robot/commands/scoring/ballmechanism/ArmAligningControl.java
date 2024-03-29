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

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.BallStorage.Alignment;
import frc.robot.subsystems.BallStorage.Direction;

/**
 *
 */
public class ArmAligningControl extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    

    private Alignment state;
    private Direction state1;


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public ArmAligningControl(Alignment state, Direction state1) { // if left is true, we're controlling left side (robot's left)
        
            requires(Robot.ballStorage);
            this.state = state;
            this.state1 = state1;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
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
        //double left = Robot.oi.getManipulatorTriggerLeft();
        //double right = Robot.oi.getManipulatorTriggerRight();
        if (state1 == Direction.CENTER){
            if (state == Alignment.LEFT){
                Robot.ballStorage.armAlign(0.6, 0); // TODO change these back to left and right
            }
            else{
                Robot.ballStorage.armAlign(0, 0.75); // TODO change these back to left and right
            }
        }
        else {
            if (state == Alignment.LEFT){
                Robot.ballStorage.armAlign(-0.6, 0); // TODO change these back to left and right
            }
            else{
                Robot.ballStorage.armAlign(0, -0.75); // TODO change these back to left and right
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return timeSinceInitialized() >= .25 || Robot.ballStorage.getPowerCells() >= 3;
        //tune
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.logEnd(this);
        Robot.ballStorage.armAlign(0, 0);

        //Robot.ballStorage.powerCellCtr(ctr++);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        Robot.logInterrupted(this);
        Robot.ballStorage.armAlign(0, 0);

    }
}
