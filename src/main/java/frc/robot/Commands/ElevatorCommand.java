package frc.robot.Commands;


import static edu.wpi.first.units.Units.Volts;

import java.lang.invoke.VolatileCallSite;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.Subsystems.Elevator.ElevatorSubsystem;

public class ElevatorCommand extends Command{
    private final ElevatorSubsystem subsystem;
    private final ElevatorFeedforward FFController;
    private final PIDController PIDController;
    private final double position;

    public ElevatorCommand(ElevatorSubsystem subsystem, double position) {
        this.subsystem = subsystem;
        this.position = position;
        addRequirements(subsystem);
        FFController = new ElevatorFeedforward(0, 0, 0);
        PIDController = new PIDController(0, 0, 0);
        
    }
    public void moveToPosition(double FPosition) {
        // extremely stupid way to clamp volts
        double PIDVolts = PIDController.calculate(subsystem.getEncoderPosition(), FPosition);
        double FFVolts =  FFController.calculateWithVelocities(subsystem.getEncoderVelocity(), 0);
        double combinedVolts = PIDVolts + FFVolts;
        subsystem.setVoltage(
            Volts.of(MathUtil.clamp(combinedVolts, 
                -ElevatorConstants.kMaxMotorVoltage, 
                ElevatorConstants.kMaxMotorVoltage)));
            SmartDashboard.putNumber("Volts", 
            
            MathUtil.clamp(combinedVolts, 
                -ElevatorConstants.kMaxMotorVoltage, 
                ElevatorConstants.kMaxMotorVoltage));
    }

    @Override
    public void initialize() {
        SmartDashboard.putNumber("goalPosition", position);
    }
    
    @Override 
    public void execute() {
        moveToPosition(position);
    }
    
    @Override
    public void end(boolean interrupted) {
        subsystem.setMotorSpeed(0);
    }
    @Override
    public boolean isFinished() {
        // stop func if position goal is greater then physical limits or if it reaches its goal.
        if(subsystem.getEncoderPosition() == position) {
            subsystem.setMotorSpeed(0);
            return true;
        }

        else {
        return false;
        }

    }
}
