package frc.team3130.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;

public class WheelOfFortune implements Subsystem {

    //Create necessary objects

    //Create and define all standard data types needed

    /**
     * The Singleton instance of this WheelOfFortune. External classes should
     * use the {@link #getInstance()} method to get the instance.
     */
    private final static WheelOfFortune INSTANCE = new WheelOfFortune();

    /**
     * Returns the Singleton instance of this WheelOfFortune. This static method
     * should be used -- {@code WheelOfFortune.getInstance();} -- by external
     * classes, rather than the constructor to get the instance of this class.
     */
    public static WheelOfFortune getInstance() {
        return INSTANCE;
    }

    private WheelOfFortune() {

    }

    public static void motorSpin(double spin) {

    }
}
