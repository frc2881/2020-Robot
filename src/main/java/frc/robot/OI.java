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
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.scoring.ballmechanism.IntakeFor7Inches;
import frc.robot.commands.background.TWINKLES;
import frc.robot.commands.background.drive.DriveWithJoysticks;
import frc.robot.commands.background.drive.IntakeSetAsBack;
import frc.robot.commands.background.drive.IntakeSetAsFront;
import frc.robot.commands.background.drive.SetArcadeDrive;
import frc.robot.commands.background.drive.SetTankDrive;
import frc.robot.commands.background.rumble.RumbleDriver;
import frc.robot.commands.background.rumble.RumbleJoysticks;
import frc.robot.commands.background.rumble.RumbleNo;
import frc.robot.commands.background.rumble.RumbleYes;
import frc.robot.commands.background.wait.DoNothing;
import frc.robot.commands.background.wait.WaitForPressure;
import frc.robot.commands.background.wait.WaitForever;
import frc.robot.commands.scoring.arm.AngleCalibrateEncoder;
import frc.robot.commands.scoring.arm.ArmControl;
import frc.robot.commands.scoring.arm.ArmToAngle;
import frc.robot.commands.scoring.ballmechanism.ArmAligningControl;
import frc.robot.commands.scoring.ballmechanism.ControlFlywheel;
import frc.robot.commands.scoring.ballmechanism.IntakeTube;
import frc.robot.commands.scoring.lift.LiftControl;
import frc.robot.commands.scoring.lift.LiftToHeight;

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
    public JoystickButton driverRightBumper;
    public JoystickButton driverPOV0;
    public JoystickButton driverPOV90;
    public JoystickButton driverPOV180;
    public JoystickButton driverPOV270;
    public JoystickButton manipulatorGreenTriangle;
    public JoystickButton manipulatorBlueX;
    public JoystickButton manipulatorPinkSquare;
    public JoystickButton manipulatorRedCircle;
    public JoystickButton manipulatorOption;
    public JoystickButton manipulatorShare;
    public JoystickButton manipulatorLeftTrigger;
    public JoystickButton manipulatorRightTrigger;
    public JoystickButton manipulatorLeftBumper;
    public JoystickButton manipulatorRightBumper;
    public JoystickButton manipulatorPOV0;
    public JoystickButton manipulatorPOV90;
    public JoystickButton manipulatorPOV180;
    public JoystickButton manipulatorPOV270;
    public Joystick driver;
    public Joystick manipulator;

    public OI() {

        manipulator = new Joystick(1);
        driver = new Joystick(0);

        // DRIVER //

        // Buttons Pad RIGHT
        driverPinkSquare = new JoystickButton(driver, 1);
        driverPinkSquare.whileHeld(new DoNothing());

        driverBlueX = new JoystickButton(driver, 2);
        driverBlueX.whenPressed(new IntakeSetAsBack());

        driverRedCircle = new JoystickButton(driver, 3);
        driverRedCircle.whileHeld(new DoNothing());

        driverGreenTriangle = new JoystickButton(driver, 4);
        driverGreenTriangle.whenPressed(new IntakeSetAsFront());

        // Small Buttons
        driverShare = new JoystickButton(driver, 9); // ARCADE DRIVE SWITCH
        driverShare.whileHeld(new SetArcadeDrive());

        driverOption = new JoystickButton(driver, 10); // TANK DRIVE SWITCH
        driverOption.whileHeld(new SetTankDrive());

        //Bumpers
        driverLeftBumper = new JoystickButton(driver, 5); // Climber Leadscrew Down TODO
        driverLeftBumper.whileHeld(new DoNothing());

        driverRightBumper = new JoystickButton(driver, 6); // Climber PistonOut + Leadscrew Up TODO
        driverRightBumper.whileHeld(new DoNothing());

        // POV Pad LEFT
        driverPOV0 = buttonFromPOV(driver, 0); // 
        driverPOV0.whileHeld(new DoNothing());

        driverPOV90 = buttonFromPOV(driver, 90); //
        driverPOV90.whileHeld(new DoNothing());

        driverPOV180 = buttonFromPOV(driver, 180); //
        driverPOV180.whileHeld(new DoNothing());

        driverPOV270 = buttonFromPOV(driver, 270); // 
        driverPOV270.whileHeld(new DoNothing());



        // MANIPULATOR //

        // Buttons Pad RIGHT
        manipulatorPinkSquare = new JoystickButton(manipulator, 1);
        manipulatorPinkSquare.whileHeld( new DoNothing());

        manipulatorBlueX = new JoystickButton(manipulator, 2); // AUTO BALL STORAGE (7" sequence) TODO
        manipulatorBlueX.whileHeld(new IntakeFor7Inches()); 
 
        manipulatorRedCircle = new JoystickButton(manipulator, 3);
        manipulatorRedCircle.whileHeld(new DoNothing());

        manipulatorGreenTriangle = new JoystickButton(manipulator, 4); // BALL STORAGE OUT
        manipulatorGreenTriangle.whileHeld(new IntakeTube(-0.5));

        // Small Buttons
        manipulatorShare = new JoystickButton(manipulator, 9); // BALL OUT - LEFT
        manipulatorShare.whileHeld(new ArmAligningControl(true, false));

        manipulatorOption = new JoystickButton(manipulator, 10); // BALL OUT - RIGHT
        manipulatorOption.whileHeld(new ArmAligningControl(false, false));

        // Triggers + Bumpers
        manipulatorLeftTrigger = new JoystickButton(manipulator, 7); // BALL CENTER - LEFT
        manipulatorLeftTrigger.whileHeld(new ArmAligningControl(true, true));

        manipulatorRightTrigger = new JoystickButton(manipulator, 8); // BALL CENTER - RIGHT
        manipulatorRightTrigger.whileHeld(new ArmAligningControl(false, true));

        manipulatorLeftBumper = new JoystickButton(manipulator, 5); // FLYWHEEL OUT
        manipulatorLeftBumper.whileHeld(new ControlFlywheel());

        manipulatorRightBumper = new JoystickButton(manipulator, 6); // FLYWHEEL FEEDER (Ball storage toward feeder)
        manipulatorRightBumper.whileHeld(new IntakeTube(1));

        // POV Pad LEFT
        manipulatorPOV180 = buttonFromPOV(manipulator, 180); // ARM HEIGHT 0
        manipulatorPOV180.whileHeld(new ArmToAngle(0));

        manipulatorPOV90 = buttonFromPOV(manipulator, 90); // ARM HEIGHT 20
        manipulatorPOV90.whileHeld(new ArmToAngle(20));

        manipulatorPOV270 = buttonFromPOV(manipulator, 270); // ARM HEIGHT 40
        manipulatorPOV270.whileHeld(new ArmToAngle(40));

        manipulatorPOV0 = buttonFromPOV(manipulator, 0); // ARM HEIGHT 60
        manipulatorPOV0.whileHeld(new ArmToAngle(60));

        // SmartDashboard Buttons
        SmartDashboard.putData("Arm Control", new ArmControl());
        SmartDashboard.putData("Angle Calibrate Encoder", new AngleCalibrateEncoder());
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("Do Nothing", new DoNothing());
        SmartDashboard.putData("Drive With Joysticks", new DriveWithJoysticks());
        SmartDashboard.putData("Lift Control", new LiftControl());
        SmartDashboard.putData("Lift To Height", new LiftToHeight());
        SmartDashboard.putData("TWINKLES", new TWINKLES());
        SmartDashboard.putData("Rumble Driver", new RumbleDriver());
        SmartDashboard.putData("Rumble Joysticks", new RumbleJoysticks());
        SmartDashboard.putData("Rumble Yes", new RumbleYes());
        SmartDashboard.putData("Rumble No", new RumbleNo());
        SmartDashboard.putData("Intake Set As Front", new IntakeSetAsFront());
        SmartDashboard.putData("Intake Set As Back", new IntakeSetAsBack());
        SmartDashboard.putData("Wait Forever", new WaitForever());
        SmartDashboard.putData("Wait For Pressure", new WaitForPressure());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    private JoystickButton buttonFromPOV(GenericHID controller, int angle) {
        return new JoystickButton(controller, angle) {
            @Override
            public boolean get() {
                if (angle == 180) {
                    return (controller.getPOV() == 180);
                } else if (angle == 90) {
                    return (controller.getPOV() == 90);
                } else if (angle == 270) {
                    return (controller.getPOV() == 270);
                } else if (angle == 0) {
                    return (controller.getPOV() == 0);
                } else {
                    return false;
                }
            }
        };
    }

    public Joystick getDriver() {
        return driver;
    }

    public Joystick getManipulator() {
        return manipulator;
    }

    // DRIVER Joysticks

    public double getDriverLeftX() {
        return driver.getRawAxis(0);
    }

    public double getDriverLeftY() {
        return driver.getRawAxis(1);
    }

    public double getDriverRightX() {
        return driver.getRawAxis(2);
    }

    public double getDriverRightY() {
        return driver.getRawAxis(5);
    }

    // DRIVER Triggers

    public double getDriverTriggerLeft() {
        return driver.getRawAxis(3);
    }

    public double getDriverTriggerRight() {
        return driver.getRawAxis(4);
    }

    // MANIPULATOR Triggers

    public double getManipulatorTriggerLeft() {
        return manipulator.getRawAxis(3);
    }

    public double getManipulatorTriggerRight() {
        return manipulator.getRawAxis(4);
    }

    // MANIPULATOR Joysticks
    public double getManipulatorLeftX() {
        return manipulator.getRawAxis(0);
    }

    public double getManipulatorLeftY() {
        return manipulator.getRawAxis(1);
    }

    public double getManipulatorRightX() {
        return manipulator.getRawAxis(2);
    }

    public double getManipulatorRightY() {
        return manipulator.getRawAxis(5);
    }

}