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

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 *
 */
public class FlywheelSwitch extends InstantCommand {

    public FlywheelSwitch() {
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.flywheel.toggleFlywheelStopped();
    }
}