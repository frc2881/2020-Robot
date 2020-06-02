package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANPIDController.AccelStrategy;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.commands.scoring.flywheel.ControlFlywheel;
import frc.robot.subsystems.Intake.RollerDirection;
import frc.robot.utils.frc4048.Logging;


public class Flywheel extends Subsystem {

    public enum FlywheelStates{FULL, HALF, STOP}

    public boolean flywheelReady = false;

    //Good Curve Numbers: P: 0.0001, I:  0.000000126275, D: 0.001
    public final double kP = 0.0001;//0.0015; //0.002
    public final double kI = 0.000000126275; //tuning 0.000001, 0.0000005, 0.00000025085, 0.000000126275,  0.0000000017
    public final double kD = 0.0015;//0.0002; //0.0012 
    public final double kIz = 0; 
    public final double kFF = 0; //0.000173; //around 1 over 6000, based off of original P value of testing
    public final double kMaxOutput = 1;
    public final double kMinOutput = 0;
    public final double maxRPM = 5700;

    private final CANSparkMax flywheel;
    private final CANEncoder flywheelEncoder;
    private final CANPIDController flywheelPID;
    private final DoubleSupplier flywheelVelocity;

    private boolean flywheelStop = true;
    private boolean flywheelFullSpeed = false;

    public Flywheel() {

        flywheel = new CANSparkMax(6, MotorType.kBrushless);
        flywheel.setInverted(true);
        flywheel.setIdleMode(IdleMode.kCoast);
        flywheel.setSmartCurrentLimit(70);

        flywheelEncoder = flywheel.getEncoder();
        flywheelVelocity = () -> flywheelEncoder.getVelocity();

        flywheelPID = flywheel.getPIDController();

        // set PID coefficients - FLYWHEEL PID LOOP
        flywheelPID.setP(kP);
        flywheelPID.setI(kI);
        flywheelPID.setD(kD);
        flywheelPID.setIZone(kIz);
        flywheelPID.setFF(kFF);
        flywheelPID.setOutputRange(kMinOutput, kMaxOutput);
    }

    public void toggleFlywheelStopped() {
        flywheelStop = !flywheelStop;
    }

    public boolean getFlywheelStopped() {
        return flywheelStop;
    }

    

    public void setFlywheelSpeedState(final FlywheelStates speed) {
        if (speed == FlywheelStates.STOP) {
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
    public void initSendable(final SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Flywheel RPM", this::getFlywheelRPM, null);
        builder.addBooleanProperty("Is Flywheel Ready", this::isFlywheelReady, null);
    }

    public boolean isFlywheelReady() {
        final boolean readyForHigh = getFlywheelRPM() > 4700 && isFlywheelFullSpeed();
        final boolean readyForLow = getFlywheelRPM() > 3300 && getFlywheelRPM() < 3700 && !isFlywheelFullSpeed();

        return (readyForHigh || readyForLow) && !flywheelStop;
    }

    public void setFlywheelRPM(final double RPM) {
        flywheelPID.setReference(RPM, ControlType.kVelocity);

    }

    public void setFlywheel(final double speed) {
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

    public Logging.LoggingContext loggingContext = new Logging.LoggingContext(Logging.Subsystems.FLYWHEEL){

        @Override
        protected void addAll(){
            add("FLYWHEEL: BusVoltage", flywheel.getBusVoltage());
            add("FLYWHEEL: OutputCurrent", flywheel.getOutputCurrent());
            add("FLYWHEEL: StickyFaults", flywheel.getStickyFaults());
        }
    };

}