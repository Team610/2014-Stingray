/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Jamie
 */
public class Catapult extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Solenoid catapultSensorSol;
    
    Timer talon;
    
    OI oi;
    Talon catapult;
    DigitalInput optical;
    private static Catapult instance;
    private DoubleSolenoid hardStopSol;

    private Catapult() {
        optical = new DigitalInput(ElectricalConstants.opticalPort);
        catapultSensorSol = new Solenoid(2, 1);
        catapultSensorSol.set(true);
        oi = OI.getInstance();
        catapult = new Talon(ElectricalConstants.catapultTalon);
        hardStopSol = new DoubleSolenoid(ElectricalConstants.shooterHardStopSol,5);
    }

    public static Catapult getInstance() {
        if (instance == null) {
            instance = new Catapult();
        }
        return instance;
    }
    public void turnOnSensor(){
        catapultSensorSol.set(true);
    }
    public void setHardStop(boolean truss) {
        if (truss) {
            hardStopSol.set(DoubleSolenoid.Value.kForward);
        } else {
            hardStopSol.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public boolean isLoading() {
//        System.out.println(!optical.get());
        return !optical.get();
    }

    public void setMain(double v) {
        
        catapult.set(v);
    }

    public void initDefaultCommand() {
    }
}