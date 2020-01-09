/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team3130.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3130.robot.subsystems.BasicTalonSRX;
import frc.team3130.robot.OI;
import frc.team3130.robot.RobotMap;
import frc.team3130.robot.subsystems.Climber;

public class SkyWalker extends CommandBase {

    public SkyWalker(){addRequirements(Climber.GetInstance());}
    private double percent;
    private BasicTalonSRX motor;

    /**
     * Spins a motor
     *
     * <p>Takes a BasicCANTalon and a percentage, and drives the motor at that percentage.
     * It keeps spinning it until another command sets the motor to off</p>
     * @param motor the BasicCANTalon to work with
     * @param percentage the percentage of the voltage supplied to the talon to pass onto the motor
     */

    public SkyWalker(BasicTalonSRX motor, double percentage) {
        addRequirements(motor);
        percent = percentage;
        this.motor=motor;
    }

    public void ChangePercent(double percentage)
    {
        percent = percentage;
    }

    // Called just before this Command runs the first time
    public void initialize(){
    }


    // Called repeatedly when this Command is scheduled to run
    public void execute() {
        Climber.motorSpin(OI.driverGamepad.getRawAxis(RobotMap.LST_BTN_X));
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        if(!OI.driverGamepad.getRawButton(RobotMap.LST_BTN_X) == true)
        {return true;}
        return !OI.driverGamepad.getRawButton(RobotMap.LST_BTN_X);
    }

    // Called once after isFinished returns true
    protected void end() {
        Climber.motorStopSpin(OI.driverGamepad.getRawAxis(RobotMap.LST_BTN_X));
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}