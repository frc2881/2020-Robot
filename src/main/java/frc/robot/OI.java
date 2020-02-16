// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AngleCalibrateEncoder;
import frc.robot.commands.ArmAligningControl;
import frc.robot.commands.ArmControl;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.CameraSwitch;
import frc.robot.commands.ControlFlywheel;
import frc.robot.commands.DoNothing;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.IntakeControlRollers;
import frc.robot.commands.IntakeSetAsBack;
import frc.robot.commands.IntakeSetAsFront;
import frc.robot.commands.LiftControl;
import frc.robot.commands.LiftToHeight;
import frc.robot.commands.PowerCellControl;
import frc.robot.commands.PowerCellControlRollers;
import frc.robot.commands.PowerCellIntake;
import frc.robot.commands.PowerCellSetRoller;
import frc.robot.commands.Rendezvous;
import frc.robot.commands.RobotPrep;
import frc.robot.commands.RumbleDriver;
import frc.robot.commands.RumbleJoysticks;
import frc.robot.commands.RumbleNo;
import frc.robot.commands.RumbleYes;
import frc.robot.commands.SetArcadeDrive;
import frc.robot.commands.SetArmAngle;
import frc.robot.commands.SetTankDrive;
import frc.robot.commands.TWINKLES;
import frc.robot.commands.TrenchPrep;
import frc.robot.commands.WaitForPressure;
import frc.robot.commands.WaitForever;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public JoystickButton driverGreenTriangle;
    public JoystickButton driverBlueX;
    public JoystickButton driverPinkSquare;
    public JoystickButton driverRedCircle;
    public JoystickButton driverPOV;
    public JoystickButton driverOption;
    public JoystickButton driverShare;
    public JoystickButton driverLeftTrigger;
    public JoystickButton driverRightTrigger;
    public JoystickButton driverLeftBumper;
    public JoystickButton driveRightBumper;
    public JoystickButton manipulatorGreenTriangle;
    public JoystickButton manipulatorBlueX;
    public JoystickButton manipulatorPinkSquare;
    public JoystickButton manipulatorRedCircle;
    public JoystickButton manipulatorPOV;
    public JoystickButton manipulatorOption;
    public JoystickButton manipulatorShare;
    public JoystickButton manipulatorLeftTrigger;
    public JoystickButton manipulatorRightTrigger;
    public JoystickButton manipulatorLeftBumper;
    public JoystickButton manipulatorRightBumper;

    public Joystick driver;
    public Joystick manipulator;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        manipulator = new Joystick(1);

        driver = new Joystick(0);

        // Driver

        driverPOV = new JoystickButton(driver, 1);
        driverPOV.whileHeld(new DoNothing());

        driverPinkSquare = new JoystickButton(driver, 1);
        driverPinkSquare.whileHeld(new DoNothing());

        driverBlueX = new JoystickButton(driver, 2);
        driverBlueX.whenPressed(new IntakeSetAsBack());

        driverShare = new JoystickButton(driver, 9);
        driverShare.whileHeld(new SetArcadeDrive());

        driverOption = new JoystickButton(driver, 10);
        driverOption.whileHeld(new SetTankDrive());

        driverRedCircle = new JoystickButton(driver, 3);
        driverRedCircle.whileHeld(new DoNothing());

        manipulatorBlueX = new JoystickButton(manipulator, 2);
        manipulatorBlueX.whileHeld(new ControlFlywheel());

        driverGreenTriangle = new JoystickButton(driver, 4);
        driverGreenTriangle.whenPressed(new IntakeSetAsFront());

        driverShare = new JoystickButton(driver, 9);
        driverShare.whileHeld(new DoNothing());

        driverOption = new JoystickButton(driver, 10);
        driverOption.whileHeld(new DoNothing());

        // MANIPULATOR
        manipulatorPinkSquare = new JoystickButton(manipulator, 1); // want to be Intake
        manipulatorPinkSquare.whileHeld(new IntakeControlRollers(1));

        manipulatorBlueX = new JoystickButton(manipulator, 2);
        manipulatorBlueX.whileHeld(new DoNothing());

        manipulatorRedCircle = new JoystickButton(manipulator, 3); // want to be Outtake
        manipulatorRedCircle.whileHeld(new IntakeControlRollers(-0.5));

        manipulatorGreenTriangle = new JoystickButton(manipulator, 4);
        manipulatorGreenTriangle.whileHeld(new DoNothing());

        manipulatorShare = new JoystickButton(manipulator, 9);
        manipulatorShare.whileHeld(new DoNothing());

        manipulatorOption = new JoystickButton(manipulator, 10);
        manipulatorOption.whileHeld(new DoNothing());

        manipulatorPOV = new JoystickButton(manipulator, 14);
        manipulatorPOV.whileHeld(new DoNothing());

        manipulatorLeftTrigger = new JoystickButton(manipulator, 7);
        manipulatorLeftTrigger.whileHeld(new ArmAligningControl(true, false));

        manipulatorRightTrigger = new JoystickButton(manipulator, 8);
        manipulatorRightTrigger.whileHeld(new ArmAligningControl(false, false));

        manipulatorRightBumper = new JoystickButton(manipulator, 6);
        manipulatorRightBumper.whileHeld(new ArmAligningControl(false, true));

        // SmartDashboard Buttons
        SmartDashboard.putData("Arm Control", new ArmControl());
        SmartDashboard.putData("Angle Calibrate Encoder", new AngleCalibrateEncoder());
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("Do Nothing", new DoNothing());
        SmartDashboard.putData("Drive With Joysticks", new DriveWithJoysticks());
        SmartDashboard.putData("Lift Control", new LiftControl());
        SmartDashboard.putData("Lift To Height", new LiftToHeight());
        SmartDashboard.putData("Set Arm Angle", new SetArmAngle());
        SmartDashboard.putData("Rendezvous", new Rendezvous());
        SmartDashboard.putData("Trench Prep", new TrenchPrep());
        SmartDashboard.putData("Robot Prep", new RobotPrep());
        SmartDashboard.putData("TWINKLES", new TWINKLES());
        SmartDashboard.putData("Rumble Driver", new RumbleDriver());
        SmartDashboard.putData("Rumble Joysticks", new RumbleJoysticks());
        SmartDashboard.putData("Rumble Yes", new RumbleYes());
        SmartDashboard.putData("Rumble No", new RumbleNo());
        SmartDashboard.putData("Intake Set As Front", new IntakeSetAsFront());
        SmartDashboard.putData("Intake Set As Back", new IntakeSetAsBack());
        SmartDashboard.putData("Camera Switch", new CameraSwitch());
        SmartDashboard.putData("Wait Forever", new WaitForever());
        SmartDashboard.putData("Wait For Pressure", new WaitForPressure());
        SmartDashboard.putData("Power Cell Control", new PowerCellControl());
        SmartDashboard.putData("Power Cell Set Roller", new PowerCellSetRoller());
        SmartDashboard.putData("Power Cell Control Rollers", new PowerCellControlRollers());
        SmartDashboard.putData("Power Cell Intake", new PowerCellIntake());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    private JoystickButton buttonFromPOV(GenericHID controller, int angle) {
        return new JoystickButton(controller, angle) {
            @Override
            public boolean get() {
                if (angle == 0) {
                    return (controller.getPOV() == 0) || (controller.getPOV() == 45) || (controller.getPOV() == 315);
                } else {
                    return (controller.getPOV() == 180) || (controller.getPOV() == 225) || (controller.getPOV() == 135);
                }
            }
        };
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriver() {
        return driver;
    }

    public Joystick getManipulator() {
        return manipulator;
    }
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS


    //DRIVER Joysticks
    
    public double getDriverLeftX() {
        return manipulator.getX(GenericHID.Hand.kLeft);
    }

    public double getDriverLeftY() {
        return manipulator.getY(GenericHID.Hand.kLeft);
    }

    public double getDriverRightX() {
        return manipulator.getX(GenericHID.Hand.kRight);
    }

    public double getDriverRightY() {
        return manipulator.getY(GenericHID.Hand.kRight);
    }

    public double getDriverPOV() {
        return driver.getPOV();
    }

    // DRIVER Triggers

    public double getDriverTriggerLeft() {
        return driver.getRawAxis(3);
    }

    public double getDriverTriggerRight() {
        return driver.getRawAxis(4);
    }

    // MANIPULATOR Joysticks

    public double getManipulatorLeftX() {
        return manipulator.getX(GenericHID.Hand.kLeft);
    }

    public double getManipulatorLeftY() {
        return manipulator.getY(GenericHID.Hand.kLeft);
    }

    public double getManipulatorRightX() {
        return manipulator.getX(GenericHID.Hand.kRight);
    }

    public double getManipulatorRightY() {
        return manipulator.getY(GenericHID.Hand.kRight);
    }

    public double getManipulatorPOV() {
        return manipulator.getPOV();
    }

    // MANIPULATOR Triggers

    public double getManipulatorTriggerLeft() {
        return manipulator.getRawAxis(3);
    }

    public double getManipulatorTriggerRight() {
        return manipulator.getRawAxis(4);
    }

}
