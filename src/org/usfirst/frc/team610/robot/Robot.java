package org.usfirst.frc.team610.robot;

import org.usfirst.frc.team610.robot.commands.DriverControls;
import org.usfirst.frc.team610.robot.constants.InputConstants;
import org.usfirst.frc.team610.robot.subsystems.BackgroundCompressor;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.subsystems.Lights;

/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** test
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    BackgroundCompressor backgroundCompressor;
    OI oi;
    Joystick driver;
    Joystick operator;
    int count = 0;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        backgroundCompressor = BackgroundCompressor.getInstance();
        oi = OI.getInstance();

        driver = oi.getDriver();

        operator = oi.getOperator();

        Lights.getInstance().setPattern(Lights.TELE);
        Scheduler.getInstance().removeAll();
       

    }

    /**
     * This function is run when autonomous mode starts.
     */
    public void autonomousInit() {
       
        

    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic() {
       

    }

    /**
     * This function is run when driver control starts.
     */
    public void teleopInit() {
        //Remove everything from the scheduler
        Scheduler.getInstance().removeAll();
        //Start driver controls
        Scheduler.getInstance().add(new DriverControls());
        Lights.getInstance().setPattern(Lights.TELE);

    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic() {



        Scheduler.getInstance().run();
    }


    public void disabledPeriodic() {
        //Post the gyro value to the dashboard
        //Change the  colour of the lights depending on the alliance
        if (operator.getRawButton(InputConstants.squareButton)) {
            Lights.getInstance().setRedAlliance(false);
        } else if (operator.getRawButton(InputConstants.oButton)) {
            Lights.getInstance().setRedAlliance(true);

        }
        if (Lights.getInstance().isRedAlliance()) {
            SmartDashboard.putString("Alliance", "Red");
            Lights.getInstance().setPattern(Lights.RED_PRE);

        } else {
            Lights.getInstance().setPattern(Lights.BLUE_PRE);

            SmartDashboard.putString("Alliance", "Blue");

        }
        
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();

    }
}
