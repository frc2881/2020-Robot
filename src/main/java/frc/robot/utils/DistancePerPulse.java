package frc.robot.utils;

import frc.robot.Robot;

public class DistancePerPulse {

    public static double get(double highestGearTeethNumber, double lowestGearTeethNumber, double wheelDiameter) {
        double gearRatio = highestGearTeethNumber/lowestGearTeethNumber;
        double wheelCircumference = wheelDiameter * Math.PI;
        return wheelCircumference / gearRatio;
    }
}