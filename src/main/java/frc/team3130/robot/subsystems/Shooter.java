package frc.team3130.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3130.robot.RobotMap;

public class Shooter implements Subsystem {

    //Create necessary objects
    private static WPI_TalonSRX m_shooterLeft;
    private static WPI_TalonSRX m_shooterRight;



    //Create and define all standard data types needed

    /**
     * The Singleton instance of this Shooter. External classes should
     * use the {@link #getInstance()} method to get the instance.
     */
    private final static Shooter INSTANCE = new Shooter();

    /**
     * Returns the Singleton instance of this Shooter. This static method
     * should be used -- {@code Shooter.getInstance();} -- by external
     * classes, rather than the constructor to get the instance of this class.
     */
    public static Shooter getInstance() {
        return INSTANCE;
    }

    private Shooter() {
        m_shooterLeft = new WPI_TalonSRX(RobotMap.CAN_SHOOTERLEFT);
        m_shooterRight = new WPI_TalonSRX(RobotMap.CAN_SHOOTERRIGHT);


        m_shooterLeft.configFactoryDefault();
        m_shooterRight.configFactoryDefault();

        /**
         * Constructor:
         * Define and configure your defined objects (ie. talons, vars)
         *
         * _talon.configFactoryDefault();
         * resets hardware defaults that could have been configured on talon before
         *
         */

    }

    public static void shooterSpin(double spin) {
        m_shooterLeft.set(spin);
        m_shooterRight.set(spin);
    }



}
