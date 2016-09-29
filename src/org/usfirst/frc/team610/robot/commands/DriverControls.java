/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.InputConstants;
import org.usfirst.frc.team610.robot.subsystems.Catapult;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author ianlo
 */
public class DriverControls extends Command {

    OI oi;
    Joystick driver;
    DriveTrain driveTrain;
    Intake intake;
    Catapult shooter;
    int driveMode = 0;
    boolean ringLightButtonPressed = false;
    boolean camLightOn = false;
    Joystick operator;
    int count = 0;
    int[] pastUltrasonicReads = new int[10];

    public DriverControls() {
        System.out.println("Driver Controls");
        oi = OI.getInstance();
        driver = oi.getDriver();
        shooter = Catapult.getInstance();
        driveTrain = DriveTrain.getInstance();
        intake = Intake.getInstance();
        driveTrain.resetEncoders();
        operator = oi.getOperator();
        Scheduler.getInstance().add(new T_GuestDrive());
        Scheduler.getInstance().add(new T_Intake());
        Scheduler.getInstance().add(new T_Catapult());


        // camera = Camera.getInstance();
        System.out.println("Driver Controls");
        driveMode = 4;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putNumber("leftEnc", driveTrain.getLeftEncoderInches());
        SmartDashboard.putNumber("rightEnc", driveTrain.getRightEncoderInches());

        //Turn the light on/off with the start button
        if (driver.getRawButton(InputConstants.startButton) && !ringLightButtonPressed) {
            ringLightButtonPressed = true;

            camLightOn = !camLightOn;

        } else if (!driver.getRawButton(InputConstants.startButton)) {
            ringLightButtonPressed = false;
        }


        if ((Math.abs(driver.getRawAxis(InputConstants.leftYAxis)) > 0.2 || Math.abs(driver.getRawAxis(InputConstants.rightXAxis)) > 0.2) && driveMode != 4) {

            driveMode = 4;

            Scheduler.getInstance().add(new T_KajDrive());

        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
