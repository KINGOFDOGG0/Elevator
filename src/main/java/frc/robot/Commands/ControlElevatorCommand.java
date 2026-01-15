package frc.robot.Commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Elevator.ElevatorSubsystem;

public class ControlElevatorCommand extends Command{
    private final ElevatorSubsystem subsystem;
    private final DoubleSupplier raiseSpeed;
    
    public ControlElevatorCommand(ElevatorSubsystem subsystem, DoubleSupplier raiseSpeed) {
    this.subsystem = subsystem;
    this.raiseSpeed = raiseSpeed;
    addRequirements(subsystem);
    }

    @Override
    public void initialize() {
    subsystem.resetEncoders();
    }

    @Override 
    public void execute() {
        SmartDashboard.putNumber("ElevatorSetSpeed", raiseSpeed.getAsDouble());
        SmartDashboard.putNumber("ElevatorActualSpeed", subsystem.getAppliedPower());
        subsystem.setMotorSpeed(0.2 * raiseSpeed.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {}
    @Override
    public boolean isFinished() {   
        return false;
    }
}
