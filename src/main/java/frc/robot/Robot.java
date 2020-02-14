/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
  private Rev2mDistanceSensor distOnboard; 

  @Override
  public void robotInit() {
    /**
     * Rev 2m distance sensor can be initialized with the Onboard I2C port
     * or the MXP port. Both can run simultaneously.
     */
    distOnboard = new Rev2mDistanceSensor(Port.kOnboard);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    /**
     * Before measurements can be read from the sensor, setAutomaticMode(true)
     * must be called. This starts a background thread which will periodically
     * poll all enabled sensors and store their measured range.
     */
    distOnboard.setAutomaticMode(true);
  }

  @Override
  public void teleopPeriodic() {
    /**
     * Range returned from the distance sensor is valid if isRangeValid()
     * returns true.
     */
    if(distOnboard.isRangeValid()) {
      SmartDashboard.putNumber("Range Onboard", distOnboard.getRange());
      SmartDashboard.putNumber("Timestamp Onboard", distOnboard.getTimestamp());
    }
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void disabledInit() {
    /**
     * The background thread may be stopped by calling setAutomaticMode(false).
     * This will command any active sensors to terminate current measurements
     * and the thread will stop.
     */
    distOnboard.setAutomaticMode(false);
  }
}


