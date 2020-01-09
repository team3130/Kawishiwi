package frc.team3130.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3130.robot.OI;
import frc.team3130.robot.subsystems.Chassis;


public class DefaultDrive extends CommandBase {

    public DefaultDrive() {
        addRequirements(Chassis.GetInstance());
    }

    /**
     * The initial subroutine of a command.  Called once when the command is initially scheduled.
     */
    @Override
    public void initialize() {

    }

    /**
     * The main body of a command.  Called repeatedly while the command is scheduled.
     * (That is, it is called repeatedly until {@link #isFinished()}) returns true.)
     */
    @Override
    public void execute() {

        double moveSpeed = -OI.driverGamepad.getRawAxis(1); //joystick's y axis is inverted
        double turnSpeed = OI.driverGamepad.getRawAxis(4); //arcade drive has left as positive, but we want right to be positive


        double turnThrottle = (0.70);
        Chassis.driveArcade(moveSpeed, turnSpeed * turnThrottle, true);
    }

    /**
     * <p>
     * Returns whether this command has finished. Once a command finishes -- indicated by
     * this method returning true -- the scheduler will call its {@link #end(boolean)} method.
     * </p><p>
     * Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Hard coding this command to always
     * return true will result in the command executing once and finishing immediately. It is
     * recommended to use * {@link edu.wpi.first.wpilibj2.command.InstantCommand InstantCommand}
     * for such an operation.
     * </p>
     *
     * @return whether this command has finished.
     */
    @Override
    public boolean isFinished() {
        return false;
    }

    /**
     * The action to take when the command ends. Called when either the command
     * finishes normally -- that is it is called when {@link #isFinished()} returns
     * true -- or when  it is interrupted/canceled. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the command.
     *
     * @param interrupted whether the command was interrupted/canceled
     */
    @Override
    public void end(boolean interrupted) {

    }
}
