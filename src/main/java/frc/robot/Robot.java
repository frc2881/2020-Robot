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

import static java.util.stream.Collectors.joining;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveForDistance;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.BallStorage;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Pneumatics;
import frc.robot.utils.NavX;
import frc.robot.utils.frc4048.Logging;
import frc.robot.utils.frc4048.WorkQueue;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Arm arm;
    //public static ControlPanel controlPanel;
    public static Drive drive;
    public static Intake intake;
    public static Lift lift;
    public static Pneumatics pneumatics;
    public static BallStorage ballStorage;
    public static Flywheel flywheel;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static NavX navX;
    public static Logging logging;

    public static double timeOfStart;
    private boolean resetRobot = true;
    private static long startTime = System.currentTimeMillis();
    public static double time;

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {
        navX = new NavX(SPI.Port.kMXP);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        arm = new Arm();
        //controlPanel = new ControlPanel();
        drive = new Drive();
        intake = new Intake();
        lift = new Lift();
        pneumatics = new Pneumatics();
        ballStorage = new BallStorage();
        flywheel = new Flywheel();

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        // (which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        HAL.report(tResourceType.kResourceType_Framework, tInstances.kFramework_RobotBuilder);

        // Add commands to Autonomous Sendable Chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        time = SmartDashboard.getNumber("Auto Pre-Delay Time", 1);
        chooser.setDefaultOption("Autonomous Command", new DriveForDistance(5));


        WorkQueue wq = new WorkQueue(512);
		logging = new Logging(100, wq);
		logging.startThread(); // Starts the logger


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        SmartDashboard.putData("Auto mode", chooser);
        SmartDashboard.putNumber("Auto Pre-Delay Time", 5);
        
        

    }

    private void resetRobot() {
        if (resetRobot) {
            pneumatics.reset();
            lift.reset();
            flywheel.reset();
            startTime = System.currentTimeMillis();
            Robot.log("Resetting robot sensors");
            resetRobot = false;
        }
    }

    /**
     * This function is called when the disabled button is hit. You can use it to
     * reset subsystems before shutting down.
     */
    @Override
    public void disabledInit() {
        printRobotMode("ROBOT IS DISABLED", "-");
        logging.traceMessage(
				"---------------------------- Robot Disabled ----------------------------");
                resetRobot = true;

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        timeOfStart = Timer.getFPGATimestamp();
        // schedule the autonomous command (example)
        printRobotMode("STARTING AUTONOMOUS", "-");
        logging.traceMessage(
				"---------------------------- Autonomous mode starting ----------------------------");
		//logging.writeAllHeadings();
		StringBuilder gameInfo = new StringBuilder();
		gameInfo.append("Match Number=");
		gameInfo.append(DriverStation.getInstance().getMatchNumber());
		gameInfo.append(", Alliance Color=");
		gameInfo.append(DriverStation.getInstance().getAlliance().toString());
		gameInfo.append(", Match Type=");
		gameInfo.append(DriverStation.getInstance().getMatchType().toString());
		logging.traceMessage( gameInfo.toString());

		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		logging.traceMessage( "Field plate selection:" + gameData);
        if (autonomousCommand != null)
            autonomousCommand.start();
        resetRobot();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
            autonomousCommand.cancel();
        if (!isCompetitionMode()) {
            resetRobot();
        }
		logging.writeAllHeadings();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        logging.writeAllData();
    }

    public static double timeSinceStart() {
        return Timer.getFPGATimestamp() - timeOfStart;
    }

    public static boolean isCompetitionMode() {
        // In Practice mode and in a real competition getMatchTime() returns time left
        // in this part of the match. Otherwise it just returns -1.0.
        return DriverStation.getInstance().getMatchTime() != -1;
    }

    private void printRobotMode(String message, String lineChar) {
        String line = IntStream.range(0, 40 - message.length()).mapToObj(n -> lineChar).collect(joining());
        System.err.println(message + line);
    }

    public static void log(String message) {
        long time = System.currentTimeMillis() - startTime;
        System.out.printf("[%6.2f] %s%n", time / 1000.0, message);
    }

    public static void logDouble(double message) {
        long time = System.currentTimeMillis() - startTime;
        System.out.printf("[%6.2f] %s%n", time / 1000.0, message);
    }
    
    public static void logInitialize(Command command) {
        log("Command " + command.getClass().getSimpleName() + " started");
    }

    public static void logInitialize(Command command, Object... settings) {
        log("Command " + command.getClass().getSimpleName() + " started: " +
                Stream.of(settings).map(Object::toString).collect(joining(", ")));
    }

    public static void logEnd(Command command) {
        log("Command " + command.getClass().getSimpleName() + " ended");
    }

    public static void logInterrupted(Command command) {
        log("Command " + command.getClass().getSimpleName() + " interrupted");
    }

    public static void logRun(InstantCommand command) {
        log("Running instant command " + command.getClass().getSimpleName());
    }

}
