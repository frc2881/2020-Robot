package frc.robot.utils;

public class DistancePerPulse {

    public static double get(double highestGearTeethNumber, double lowestGearTeethNumber, double encoderCountsPerRevolution, double wheelDiameter) {
        double ticksPerRevolution = encoderCountsPerRevolution;
        double gearRatio = highestGearTeethNumber/lowestGearTeethNumber;
        double wheelCircumference = wheelDiameter * Math.PI; 
        return wheelCircumference / ticksPerRevolution / gearRatio;
    }
}