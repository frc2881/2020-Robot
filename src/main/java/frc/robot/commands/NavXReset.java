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

import frc.robot.Robot;
import frc.robot.commands.WaitUntilNavXDetected;

/**
 *
 */
public class NavXReset extends WaitUntilNavXDetected {

    @Override
    protected void initialize() {
        Robot.logInitialize(this);
        Robot.navX.reset();

        
        // TODO: implement this
    }

    @Override
    protected void end() {
        Robot.logEnd(this);
    }
}