package frc.team3130.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3130.robot.subsystems.ExampleSubsystem;
import frc.team3130.robot.subsystems.WheelOfFortune;

import java.util.Set;

public class ColorAlignment implements Command {
    private final Set<Subsystem> subsystems;
    //TODO: make field color dependent on inputs from field
    private static String fieldColor;


    //used to terminate the program
    private static boolean colorFound;

    public ColorAlignment() {
        this.subsystems = Set.of(WheelOfFortune.getInstance());

        colorFound = false;
    }

    /**
     * The initial subroutine of a command.  Called once when the command is initially scheduled.
     */
    @Override
    public void initialize() {

        colorFound = false;
        fieldColor = "Red";
        System.out.println("EPIC");
    }

    /**
     * The main body of a command.  Called repeatedly while the command is scheduled.
     * (That is, it is called repeatedly until {@link #isFinished()}) returns true.)
     */
    @Override
    public void execute() {
        String color = WheelOfFortune.detectColor();

        //RED
        // color red and field red
        if (color.equals("Red") && fieldColor.equals("Red")) {
            System.out.println("DETECTING RED");
            WheelOfFortune.motorSpin(0.5);
            if (color.equals("Blue")) {
                WheelOfFortune.motorSpin(0);
                colorFound = true;
                System.out.println(colorFound + " is value of colorFound");
            }
        }
        // Red Green
        if (color.equals("Red") && fieldColor.equals("Green")) {
            WheelOfFortune.motorSpin(0.5);
            if (color.equals("Yellow")) {
                WheelOfFortune.motorSpin(0);
                colorFound = true;
            }
        }
        //Red Yellow
        if (color.equals("Red") && fieldColor.equals("Yellow")) {
            WheelOfFortune.motorSpin(-0.5);
            if (color.equals("Green")) {
                WheelOfFortune.motorSpin(0);
                colorFound = true;
            }
        }
        /*END OF RED

        BLUE

        Blue and Blue */
        if (color.equals("Blue") && fieldColor.equals("Blue")) {
            WheelOfFortune.motorSpin(0.5);
            if (color.equals("Red")) {
                WheelOfFortune.motorSpin(0);
                colorFound = true;
            }
        }
        // Blue Yellow
        if (color.equals("Blue") && fieldColor.equals("Yellow")) {
            WheelOfFortune.motorSpin(0.5);
            if (color.equals("Green")) {
                WheelOfFortune.motorSpin(0);
                colorFound = true;
            }
        }
        // Blue Green
        if (color.equals("Blue") && fieldColor.equals("Green")) {
            WheelOfFortune.motorSpin(-0.5);
            if (color.equals("Yellow")) {
                WheelOfFortune.motorSpin(0);
                colorFound = true;
            }
        }
        /*END OF BLUE

        GREEN

        Green 1 to (Yellow)*/
        if (color.equals("Green") && fieldColor.equals("Green")) {
            WheelOfFortune.motorSpin(0.5);
            if (color.equals("Yellow")) {
                WheelOfFortune.motorSpin(0);
                colorFound = true;
            }

        }
        //Green 2 (to Blue)
        if (color.equals("Green") && fieldColor.equals("Blue")) {
            WheelOfFortune.motorSpin(0.5);
            if (color.equals("Red")) {
                WheelOfFortune.motorSpin(0);
                colorFound = true;
            }
        }
        //Green 3 (to Blue for Red)
        if (color.equals("Green") && fieldColor.equals("Red")) {
            System.out.println("DETECTING GREEN");
            WheelOfFortune.motorSpin(-0.5);
            if (color.equals("Blue")) {
                WheelOfFortune.motorSpin(0);
                colorFound = true;
            }
        }
        /*END OF GREEN

        YELLOW

        Yellow to Green (for Yellow) */
        if (color.equals("Yellow") && fieldColor.equals("Yellow")) {
            WheelOfFortune.motorSpin(0.5);
            if (color.equals("Green")) {
                WheelOfFortune.motorSpin(0);
                colorFound = true;
            }
        }
        //Yellow 2 (to Blue for Red)
        if (color.equals("Yellow") && fieldColor.equals("Red")) {
            System.out.println("DETECTING YELLOW");
            WheelOfFortune.motorSpin(0.5);
            if (color.equals("Blue")) {
                WheelOfFortune.motorSpin(0);
                colorFound = true;
            }
        }
        //Yellow 3 (to Red for Blue)
        if (color.equals("Yellow") && fieldColor.equals("Blue")) {
            WheelOfFortune.motorSpin(-0.5);
            if (color.equals("Red")) {
                WheelOfFortune.motorSpin(0);
                colorFound = true;
            }
        }
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
        //Code should turn off now
        if (colorFound) {
            return true;
        }
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

    /**
     * <p>
     * Specifies the set of subsystems used by this command.  Two commands cannot use the same
     * subsystem at the same time.  If the command is scheduled as interruptible and another
     * command is scheduled that shares a requirement, the command will be interrupted.  Else,
     * the command will not be scheduled. If no subsystems are required, return an empty set.
     * </p><p>
     * Note: it is recommended that user implementations contain the requirements as a field,
     * and return that field here, rather than allocating a new set every time this is called.
     * </p>
     *
     * @return the set of subsystems that are required
     */
    @Override
    public Set<Subsystem> getRequirements() {
        return this.subsystems;
    }
}
