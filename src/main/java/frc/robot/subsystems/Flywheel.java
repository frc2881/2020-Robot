package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.scoring.ballmechanism.ControlFlywheel;
import frc.robot.subsystems.Intake.RollerDirection;


public class Flywheel extends Subsystem {


    private CANSparkMax flywheel;

    private Solenoid flywheelSolenoid;


    public Flywheel() {

    flywheel = new CANSparkMax(6, MotorType.kBrushless);
    flywheel.setInverted(false);
    flywheel.setIdleMode(IdleMode.kCoast);

    flywheelSolenoid = new Solenoid(0, 0);
    addChild("Flywheel Solenoid", flywheelSolenoid);

    }
   
    public void intakeFlywheel(double speed, RollerDirection state) {
        if (state == RollerDirection.INTAKE) {
            flywheel.set(-speed);
        } else {
            // EJECT
            flywheel.set(speed);
        }
    }

    public void flywheel(double speed) {
        flywheel.set(speed);
    }

    public boolean getIntakeFlywheel() {
        return (flywheel.get() > 0.05);
        // return if sufficient speed?
    }

    public void setFlywheel(double speed) {
        flywheel.set(speed);
    }

    @Override
    public void initDefaultCommand() {

        setDefaultCommand(new ControlFlywheel(-0.45));

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

}