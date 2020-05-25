package frc.robot.commands.scoring.arm;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class CalibrateArmEncoder extends InstantCommand {

    private boolean autonomous;

    public CalibrateArmEncoder(boolean autonomous) {
        this.autonomous = autonomous;
    }

    @Override
    protected void initialize() {
        Robot.log("Calibrate Arm Encoder has started");
        //Robot.arm.resetArmEncoder(autonomous);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        Robot.log("Calibrate Arm Encoder has finished");
    }
}