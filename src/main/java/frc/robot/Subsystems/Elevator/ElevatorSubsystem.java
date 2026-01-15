package frc.robot.Subsystems.Elevator;

import frc.robot.Constants.ElevatorConstants;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Second;
import static edu.wpi.first.units.Units.Seconds;
import static edu.wpi.first.units.Units.Volts;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Voltage;



public class ElevatorSubsystem extends SubsystemBase{
    private final SparkMax m_motor;
    private final RelativeEncoder m_encoder;
    private SysIdRoutine SysID;


    public ElevatorSubsystem() {
        m_motor = new SparkMax(0, MotorType.kBrushless);
        m_encoder = m_motor.getEncoder();

    }

    public void ConfigureSysIDRoutine() {
        SysID = 
        new SysIdRoutine(
            new SysIdRoutine.Config(Volts.per(Second).of(1.0),
             Volts.of(2), 
             Seconds.of(5)), 
        new SysIdRoutine.Mechanism(
            (voltage) -> {
                setVoltage(voltage);
        },
        (log) -> {
            log.motor("SysIDMotorOne").
            linearPosition(getLinearPosition()).
            linearVelocity(getLinearVelocity()).
            voltage(getVoltage());
        },
        this));
    }

    public void setMotorSpeed(double speed) {
        if(ElevatorConstants.kClampBatteryVoltageToMaxVoltage)
        {
            m_motor.setVoltage(Math.max(Math.min(speed, 1.0), -1.0) * ElevatorConstants.kMaxMotorVoltage);
        }
        else
        {
            m_motor.setVoltage(speed * ElevatorConstants.kMaxMotorVoltage);
        }
    }
    public void setVoltage(Voltage voltage) {
        m_motor.setVoltage(voltage);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("ElevatorPosition", getEncoderPosition());
    }
    public void resetEncoders() {
        m_encoder.setPosition(0);
    }
    
    public double getEncoderPosition() {
        
        return m_encoder.getPosition() * ElevatorConstants.kElevatorEncoderRot2Meters;
    }

    public double getEncoderVelocity() {

        return m_encoder.getVelocity() * ElevatorConstants.kElevatorEncoderRot2Meters / 60;
    }

    public double getAppliedPower() {
        return m_motor.getAppliedOutput();
    }

    public Distance getLinearPosition() {
        return Meters.of(getEncoderPosition());
    }
    
    public LinearVelocity getLinearVelocity() {
        return MetersPerSecond.of(getEncoderVelocity());
    }
    public Voltage getVoltage() {
        return Volts.of(getAppliedPower() * m_motor.getBusVoltage());
    }
    
    public Command getQuasiStaticForward() {return SysID.quasistatic(SysIdRoutine.Direction.kForward);}
    public Command getQuasiStaticReverse() {return SysID.quasistatic(SysIdRoutine.Direction.kReverse);}
    public Command getDynamicForward() {return SysID.dynamic(SysIdRoutine.Direction.kForward);}
    public Command getDynamicReverse() {return SysID.dynamic(SysIdRoutine.Direction.kReverse);}
    
}
