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
    private boolean flywheelSpeed = false;

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

    public void setFlywheelSpeed(boolean halfSpeed) {
        
        //flywheelSpeed = halfSpeed;
    }

    public boolean isFlywheelSpeed() {
        return flywheelSpeed;
    }
   
    public void reset() {
        flywheelReady = false;
        flywheelSpeed = false;
        flywheelStop = false;
    }
    
    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Flywheel RPM", this::getFlywheelRPM, null);
        builder.addBooleanProperty("Is Flywheel Ready", this::isFlywheelReady, null);
    }

    public boolean isFlywheelReady() {
        boolean readyForHigh = getFlywheelRPM() > 4000 && isFlywheelSpeed();
        boolean readyForLow = getFlywheelRPM() > 2000 && getFlywheelRPM() < 2400 && !isFlywheelSpeed();

        return readyForHigh || readyForLow;
    }

    public void intakeFlywheel(double speed) {
            flywheel.set(speed);
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

    public enum FlywheelStates {
        STOP, GO
    }

    public void setFlywheelSpeedState(FlywheelStates speed) {

    }

    @Override
    public void initDefaultCommand() {

        setDefaultCommand(new ControlFlywheel(0));

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

}