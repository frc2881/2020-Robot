// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.commands.autonomous.autoCommands;

import frc.robot.commands.autonomous.autoCommands.Enums.StartingPosition;
import frc.robot.commands.background.wait.WaitForever;
import frc.robot.commands.background.wait.WaitUntilFlywheelReady;
import frc.robot.commands.scoring.arm.ArmToAngle;
import frc.robot.commands.scoring.arm.CalibrateArmEncoder;
import frc.robot.commands.scoring.ballmechanism.AutoFiringSequence;
import frc.robot.commands.scoring.flywheel.SetFlywheelSpeed;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Flywheel.FlywheelStates;

/**
 *
 */
public class AutoScore extends AbstractAutoCommand {

    AutoScore(StartingPosition start) {

        double angle = (start == StartingPosition.FRONT_ON_TEN_FOOT_ARC) ? Arm.frontBumperAngle : Arm.backBumperAngle;
        addSequential(new ArmToAngle(0), 3);
        addSequential(new CalibrateArmEncoder(false));
        addSequential(new SetFlywheelSpeed(FlywheelStates.HALF));
        addSequential(new ArmToAngle(angle), 1);
        addSequential(new WaitForever(), 3);
    }

}
