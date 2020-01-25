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

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.DriveWithJoysticks;

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
private DifferentialDrive differentialDrive1;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private DifferentialDrive differentialDrive2;

    public Drive() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
leftFront = new CANSparkMax(1, MotorType.kBrushless);

leftFront.restoreFactoryDefaults();  
leftFront.setInverted(false);
leftFront.setIdleMode(IdleMode.kCoast);
  
        
leftRear = new CANSparkMax(2, MotorType.kBrushless);

leftRear.restoreFactoryDefaults();  
leftRear.setInverted(false);
leftRear.setIdleMode(IdleMode.kCoast);
  
        


//Set SlaveSpeedControllers to Follow MasterSpeedController
leftRear.follow(leftFront);
        
        
        
rightFront = new CANSparkMax(3, MotorType.kBrushless);

rightFront.restoreFactoryDefaults();  
rightFront.setInverted(false);
rightFront.setIdleMode(IdleMode.kCoast);
  
        
rightRear = new CANSparkMax(4, MotorType.kBrushless);

rightRear.restoreFactoryDefaults();  
rightRear.setInverted(false);
rightRear.setIdleMode(IdleMode.kCoast);
  
        


//Set SlaveSpeedControllers to Follow MasterSpeedController
rightRear.follow(rightFront);
        
        
        
differentialDrive1 = new DifferentialDrive(leftFront, rightFront);
addChild("Differential Drive 1",differentialDrive1);
differentialDrive1.setSafetyEnabled(true);
differentialDrive1.setExpiration(0.1);
differentialDrive1.setMaxOutput(1.0);

        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

hMotorRight = new CANSparkMax(5, MotorType.kBrushless);
hMotorRight.restoreFactoryDefaults();  
hMotorRight.setInverted(false);
hMotorRight.setIdleMode(IdleMode.kCoast);
hMotorRight.follow(rightFront);

hMotorLeft = new CANSparkMax(5, MotorType.kBrushless);
hMotorLeft.restoreFactoryDefaults();  
hMotorLeft.setInverted(false);
hMotorLeft.setIdleMode(IdleMode.kCoast);
hMotorLeft.follow(rightFront);

differentialDrive2 = new DifferentialDrive(hMotorLeft, hMotorRight);
addChild("Differential Drive 2",differentialDrive1);
differentialDrive2.setSafetyEnabled(true);
differentialDrive2.setExpiration(0.1);
differentialDrive2.setMaxOutput(1.0);

    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DriveWithJoysticks());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void tankDrive(double leftSpeed, double rightSpeed) {
        
        // if(intakeLocation == IntakeLocation.FRONT) {
        //     differentialDrive.tankDrive(leftSpeed, rightSpeed, true);
        // }
        // else {
        //     differentialDrive.tankDrive(-rightSpeed, -leftSpeed, true);
        // }
        differentialDrive1.tankDrive(leftSpeed, rightSpeed, true);
        System.out.println("tankDrive");
    }

}

