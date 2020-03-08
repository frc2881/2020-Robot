// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.background.drive.DriveWithJoysticks;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class Drive extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private CANSparkMax leftFront;
    private CANSparkMax leftRear;
    private SpeedControllerGroup left;
    private CANSparkMax rightFront;
    private CANSparkMax rightRear;
    private SpeedControllerGroup right;
    private DifferentialDrive differentialDrive;
    private Spark spotlight;
    //private CANSparkMax hMotorRight;
    //private CANSparkMax hMotorLeft;
    // fix in RobotBuilder later

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    //private DifferentialDrive differentialDrive2;
    private PIDController turnPID;
    private double rotateToAngleRate;
    private CANSparkMax hDrive;
    private boolean useSplitArcade;

    public Drive() {
        setUseSplitArcade(true);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        leftFront = new CANSparkMax(1, MotorType.kBrushless);
        leftFront.setInverted(false);
        leftFront.setIdleMode(IdleMode.kBrake);
        leftFront.setSmartCurrentLimit(80);

        leftRear = new CANSparkMax(2, MotorType.kBrushless);
        leftRear.setInverted(false);
        leftRear.setIdleMode(IdleMode.kBrake);
        leftRear.setSmartCurrentLimit(80);
        leftRear.follow(leftFront); // Set SlaveSpeedControllers to Follow MasterSpeedController
        //left =  new SpeedControllerGroup(leftFront, leftRear);



        rightFront = new CANSparkMax(3, MotorType.kBrushless);
        rightFront.setInverted(false);
        rightFront.setIdleMode(IdleMode.kBrake);
        rightFront.setSmartCurrentLimit(80);

        rightRear = new CANSparkMax(4, MotorType.kBrushless);
        rightRear.setInverted(false);
        rightRear.setIdleMode(IdleMode.kBrake);
        rightRear.setSmartCurrentLimit(80);
        rightRear.follow(rightFront); // Set SlaveSpeedControllers to Follow MasterSpeedController
        //right = new SpeedControllerGroup(rightFront, rightRear);



        differentialDrive = new DifferentialDrive(leftFront, rightFront);
        addChild("Differential Drive 1", differentialDrive);
        differentialDrive.setSafetyEnabled(true);
        differentialDrive.setExpiration(0.1);
        differentialDrive.setMaxOutput(1.0);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        hDrive = new CANSparkMax(5, MotorType.kBrushless);
        hDrive.setInverted(false);
        hDrive.setIdleMode(IdleMode.kBrake);
        hDrive.setSmartCurrentLimit(40);

        spotlight = new Spark(0);
        spotlight.addChild(spotlight);
    }

    @Override
    public void initDefaultCommand() {

        setDefaultCommand(new DriveWithJoysticks());


        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }



    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void arcadeDrive(double xSpeed, double zRotation) {

        differentialDrive.arcadeDrive(xSpeed, -zRotation, false);
    }

    //DO NOT DELETE. ONLY USED WITH TURN TO ANGLE. DONT DELETE
    //public void tankDrive(double leftSpeed, double rightSpeed) {

    //    differentialDrive.tankDrive(-leftSpeed, -rightSpeed, true);
    //}

    /*
     * public void autonomousRotate(double leftSpeed, double rightSpeed) { // DONT
     * Use 'squaredInputs' or deadband in autonomous driveTrain.setDeadband(0);
     * driveTrain.tankDrive(leftSpeed, rightSpeed, false); }
     */
    public boolean getUseSplitArcade() {
        return this.useSplitArcade;
    }

    public void setUseSplitArcade(boolean useSplitArcade) {
        this.useSplitArcade = useSplitArcade;
    }

    public void setDeadband(double deadband) {
        differentialDrive.setDeadband(deadband);
    }

    public void hDrive(double leftTrigger, double rightTrigger) {
        if (leftTrigger > -1 && rightTrigger > -1) {
            hDrive.set(0);
        } else if (leftTrigger > -1) {
            hDrive.set((leftTrigger + 1) / 2);
        } else if (rightTrigger > -1) {
            hDrive.set(-(rightTrigger + 1) / 2);
        } else {
            hDrive.set(0);
        }
    }

    public void setSpotlight(boolean on) {
        spotlight.set(on ? 1 : 0);
    }
    /*
     * set speed to +1, /2 -1 to 1
     */
}