package frc.robot;

import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.util.Units;

public class Constants {
    public static final class ElevatorConstants {

        

    public static final int kElevatorMotorPort = 0;

    public static final double kPulleyDiameterMeter = Units.inchesToMeters(1.504);
    public static final double kElevatorMotorGearRatio = 1 / 15;
    public static final double kBeltPullMeters = kElevatorMotorGearRatio * Math.PI * kPulleyDiameterMeter;
    public static final double kElevatorEncoderRot2Meters = 2 * kBeltPullMeters;
    public static final double kMaxMotorVoltage = 11; // 


    public static final SparkMaxConfig kElevatorMotorConfig = new SparkMaxConfig();


        public static final boolean kClampBatteryVoltageToMaxVoltage = true;
        public static final double kGravityOpposition = 0.25;
        public static final double kTimeBetweenCommands = 0.02;
        public static final double kElevatorHeightLimit = 0.0;
        


        // LEVELS
        public static final double kFirstLevel = Units.inchesToMeters(0.05);
        // public static final double kSecondLevel = Units.inchesToMeters(12.49 + 4); //12.49
        // public static final double kThirdLevel = Units.inchesToMeters(28.33 + 4); //28.33
        // public static final double kFourthLevel = Units.inchesToMeters(53.36); //54.36

        public static final double kSecondLevel = Units.inchesToMeters(31); // if 31 is right, 16 more will get l3, 40+31 will get l4
        public static final double kThirdLevel = Units.inchesToMeters(31+16); // 
        public static final double kFourthLevel = Units.inchesToMeters(40+31); //54.36

        public static final double kSourceIntake = Units.inchesToMeters(16.6);
    }
}
