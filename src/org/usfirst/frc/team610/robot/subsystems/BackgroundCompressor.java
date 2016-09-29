/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author ianlo
 */
public class BackgroundCompressor extends Subsystem {

    private Compressor compressor;
    private static BackgroundCompressor instance;

    private BackgroundCompressor() {
        //Initialize the compressor and start it once the instance is created.
        compressor = new Compressor();
        System.out.println(compressor.getPressureSwitchValue());
        compressor.start();
        System.out.println("Compressor started");
    }


//Get the singleton instance of the compressor.
    public static BackgroundCompressor getInstance() {
        if (instance == null) {
            instance = new BackgroundCompressor();
        }
        return instance;
    }

    protected void initDefaultCommand() {
    }
}
