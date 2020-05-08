package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.commands.scoring.flywheel.ControlFlywheel;
import frc.robot.subsystems.Intake.RollerDirection;


public class Flywheel extends Subsystem {

    public enum FlywheelStates{FULL, HALF, STOP}

    public boolean flywheelReady = false;

    private CANSparkMax flywheel;
    private CANEncoder flywheelEncoder;
    private DoubleSupplier flywheelVelocity;

    private boolean flywheelStop = true;
    private boolean flywheelFullSpeed = false;

    public Flywheel() {

        flywheel = new CANSparkMax(6, MotorType.kBrushless);
        flywheel.setInverted(true);
        flywheel.setIdleMode(IdleMode.kCoast);
        flywheel.setSmartCurrentLimit(70);

        flywheelEncoder = flywheel.getEncoder();
        flywheelVelocity = () -> flywheelEncoder.getVelocity();
    }

    public void toggleFlywheelStopped() {
        flywheelStop = !flywheelStop;
    }

    public boolean getFlywheelStopped() {
        return flywheelStop;
    }

    public void setFlywheelSpeedState(FlywheelStates speed) {
        if (speed == FlywheelStates.STOP){
            flywheelStop = true;
        } else {
            flywheelStop = false;
            flywheelFullSpeed = speed == FlywheelStates.FULL;
        }
    }

    public boolean isFlywheelFullSpeed() {
        return flywheelFullSpeed;
    }
   
    public void reset() {
        flywheelReady = false;
        flywheelFullSpeed = false;
        flywheelStop = true;
    }
    
    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Flywheel RPM", this::getFlywheelRPM, null);
        builder.addBooleanProperty("Is Flywheel Ready", this::isFlywheelReady, null);
    }

    public boolean isFlywheelReady() {
        boolean readyForHigh = getFlywheelRPM() > 4700 && isFlywheelFullSpeed();
        boolean readyForLow = getFlywheelRPM() > 3300 && getFlywheelRPM() < 3700 && !isFlywheelFullSpeed();

        return (readyForHigh || readyForLow) && !flywheelStop;
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