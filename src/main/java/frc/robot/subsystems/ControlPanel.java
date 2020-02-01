package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;

public class ControlPanel extends Subsystem {
    private CANSparkMax panelSpinner;
    private final I2C.Port i2cPort = I2C.Port.kOnboard;

    
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  
    private final ColorMatch m_colorMatcher = new ColorMatch();
  
    private final Color kBlueTarget = ColorMatch.makeColor(0.125, 0.45, 0.418);
    private final Color kGreenTarget = ColorMatch.makeColor(0.165, 0.583, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.521, 0.355, 0.123);
    private final Color kYellowTarget = ColorMatch.makeColor(0.318, 0.568, 0.114);

    public ControlPanel() {
        panelSpinner= new CANSparkMax(6, MotorType.kBrushless);

        panelSpinner.restoreFactoryDefaults();  
        panelSpinner.setInverted(false);
        panelSpinner.setIdleMode(IdleMode.kBrake);

        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget); 
    }

    @Override
    public void initDefaultCommand() {
        
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
    
    }

    public ColorMatchResult getColor()
    {
        Color detectedColor = m_colorSensor.getColor();
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
        return match;
    
    }

    public void spinMotor(double speed) {
        panelSpinner.set(speed);
    }

}