package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.commands.scoring.flywheel.ControlFlywheel;
import frc.robot.subsystems.Intake.RollerDirection;


public class Flywheel extends Subsystem {

    public boolean flywheelReady = false;

    private CANSparkMax flywheel;
    private CANEncoder flywheelEncoder;
    private Solenoid flywheelSolenoid;
    private DoubleSupplier flywheelVelocity;

    private boolean flywheelStop = false;
    private boolean flywheelFullSpeed = false;

    public Flywheel() {

    flywheel = new CANSparkMax(6, MotorType.kBrushless);
    flywheel.setInverted(true);
    flywheel.setIdleMode(IdleMode.kCoast);

    flywheelEncoder = flywheel.getEncoder();
    flywheelVelocity = () -> flywheelEncoder.getVelocity();

    flywheelSolenoid = new Solenoid(0, 0);
    addChild("Flywheel Solenoid", flywheelSolenoid);

    }

    public void toggleFlywheelStopped() {
        flywheelStop = !flywheelStop;
    }

    public boolean getFlywheelStopped() {
        return flywheelStop;
    }

    public void setFlywheelSpeed(boolean fullSpeed) {
        flywheelFullSpeed = fullSpeed;
    }

    public boolean isFlywheelFullSpeed() {
        return flywheelFullSpeed;
    }
   
    public void reset() {
        flywheelReady = false;
        flywheelFullSpeed = false;
        flywheelStop = false;
    }
    
    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Flywheel RPM", this::getFlywheelRPM, null);
        builder.addBooleanProperty("Is Flywheel Ready", this::isFlywheelReady, null);
    }

    public boolean isFlywheelReady() {
        boolean readyForHigh = getFlywheelRPM() > 4000 && flywheel.get() == 0.85;
        boolean readyForLow = getFlywheelRPM() > 2000 && flywheel.get() == 0.45;

        return readyForHigh || readyForLow;
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

    public double getFlywheelRPM() {
        return flywheelVelocity.getAsDouble();
    }

    @Override
    public void initDefaultCommand() {

        setDefaultCommand(new ControlFlywheel());

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

}