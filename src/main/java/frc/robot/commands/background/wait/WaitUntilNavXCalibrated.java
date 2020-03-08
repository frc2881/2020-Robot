package frc.robot.commands.background.wait;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WaitUntilNavXCalibrated extends Command {
    @Override
    protected void initialize() {
        Robot.log("Wait Until NavX Calibration has started");
    }

    @Override
    protected boolean isFinished() {
        return Robot.isNavXReady();
    }

    @Override
    protected void end() {
        Robot.log("Wait Until NavX Calibration has finished");
    }
}