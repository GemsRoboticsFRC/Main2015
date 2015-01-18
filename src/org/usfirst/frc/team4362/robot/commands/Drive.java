package org.usfirst.frc.team4362.robot.commands;

import org.usfirst.frc.team4362.robot.RobotMap;
import org.usfirst.frc.team4362.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;




/**
 *
 */
public class Drive extends CommandBase {
	private Joystick leftStick,rightStick;
	private Button leftTrigger, rightTrigger;
	private Solenoid shifter;
	private DriverStation m_ds;
    public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(chassis);
    	leftStick = oi.getLeftStick();
    	rightStick = oi.getRightStick();
    	leftTrigger = oi.getLeftTigger();
    	rightTrigger = oi.getRightTrigger();
    	shifter = oi.getShifter();
    	m_ds = DriverStation.getInstance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	chassis.tankDrive(0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(leftTrigger.get()){
    		shifter.set(true);
    	}
    	if(rightTrigger.get()){
    		shifter.set(false);
    	}
    	double left = leftStick.getRawAxis(1);
    	double right = rightStick.getRawAxis(1);
    	chassis.tankDrive(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return !m_ds.isOperatorControl();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
