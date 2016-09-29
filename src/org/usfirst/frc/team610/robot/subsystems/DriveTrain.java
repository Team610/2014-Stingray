/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team610.robot.subsystems;


import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author ianlo
 */
public class DriveTrain extends Subsystem {

    private static DriveTrain instance;
    private Talon leftFront, leftMiddle, leftBack;
    private Talon rightFront, rightMiddle, rightBack;
    private Encoder leftEncoder, rightEncoder;

    public DriveTrain() {
        //Intialize the talons according to their channels.
        leftFront = new Talon(ElectricalConstants.talonDriveLeftFront);
        leftMiddle = new Talon(ElectricalConstants.talonDriveLeftMiddle);
        leftBack = new Talon(ElectricalConstants.talonDriveLeftBack);
        rightFront = new Talon(ElectricalConstants.talonDriveRightFront);
        rightMiddle = new Talon(ElectricalConstants.talonDriveRightMiddle);
        rightBack = new Talon(ElectricalConstants.talonDriveRightBack);
        
        //Initialize and start the encoders.
        leftEncoder = new Encoder(ElectricalConstants.leftEncoderASource, ElectricalConstants.leftEncoderBSource);
        rightEncoder = new Encoder(ElectricalConstants.rightEncoderASource, ElectricalConstants.rightEncoderBSource);
        rightEncoder.setReverseDirection(true);
        //Create the gyro, set it sensitivity, and start it at 0 degrees.
     

    }

    //Get the singleton instance of drivetrain.
    static public DriveTrain getInstance() {
        if (instance == null) {
            instance = new DriveTrain();
        }
        return instance;
    }  
    //Start KajDrive when the drivetrain is first created.

    protected void initDefaultCommand() {
    }

    public void resetEncoders() {
        rightEncoder.reset();
        leftEncoder.reset();
    }

    //Return the gyro's reading rounded to the nearest int.
    //Return the left encoder value in inches.

    public double getLeftEncoderInches() {
        return toInches(leftEncoder.get());

    }
    //Return the right encoder value in inches.

    public double getRightEncoderInches() {
        return toInches(rightEncoder.get());
    }
    //Set the left side of the drivetrain using vbus

    public void setLeftVBus(double value) {
        leftFront.set(value);
        leftMiddle.set(value);
        leftBack.set(value);
    }
    //Set the right side of the drivetrain using vbus.

    public void setRightVBus(double value) {
        rightFront.set(-value);
        rightMiddle.set(-value);
        rightBack.set(-value);
    }
    //Mr. Lim's equation to convert encoder counts to inches.

    private double toInches(int encCount) {
        return ((int) (encCount / 10.24 * Math.PI * 6 + 0.5)) / 100.0 * 3.8;
    }
}
