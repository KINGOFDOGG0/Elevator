// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Commands.ControlElevatorCommand;
import frc.robot.Commands.ElevatorCommand;
import frc.robot.Commands.RaiseElevatorCommand;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.Subsystems.Elevator.ElevatorSubsystem;

public class RobotContainer {
  private final ElevatorSubsystem m_subsystem = new ElevatorSubsystem();
  private final Joystick m_Joystick = new Joystick(0);
  // public final RaiseElevatorCommand raiseElevatorCommand;
  

  public RobotContainer() {
    configureBindings();
      m_subsystem.setDefaultCommand(new ControlElevatorCommand(m_subsystem, () -> -m_Joystick.getRawAxis(1)));
      // raiseElevatorCommand = new RaiseElevatorCommand(m_subsystem);
  }

  private void configureBindings() {
    JoystickButton button_a = new JoystickButton(m_Joystick, 1);
    JoystickButton button_b = new JoystickButton(m_Joystick, 2);
    JoystickButton button_x = new JoystickButton(m_Joystick, 3);
    JoystickButton button_y = new JoystickButton(m_Joystick, 4);

    button_a.whileTrue(m_subsystem.getQuasiStaticForward());
    button_b.whileTrue(m_subsystem.getQuasiStaticReverse());
    button_x.whileTrue(m_subsystem.getDynamicForward());
    button_y.whileTrue(m_subsystem.getDynamicReverse());







    // new POVButton(m_Joystick, 0).onTrue(new ElevatorCommand(m_subsystem, ElevatorConstants.kFirstLevel));

  }

}