package frc.team3130.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3130.robot.OI;
import frc.team3130.robot.RobotMap;



public class Climber implements Subsystem {


    /**
     * The Singleton instance of this Climber. External classes should
     * use the {@link #getInstance()} method to get the instance.
     */
    private final static Climber INSTANCE = new Climber();

    /**
     * Returns the Singleton instance of this Climber. This static method
     * should be used -- {@code Climber.getInstance();} -- by external
     * classes, rather than the constructor to get the instance of this class.
     */
    public static Climber getInstance() {
        return INSTANCE;
    }


    private static WPI_TalonSRX m_skyWalker;

    private Climber() {
        m_skyWalker = new WPI_TalonSRX(RobotMap.CAN_SKYWALKER);
        m_skyWalker.configFactoryDefault();
    }

    public static void motorSpin (double spin){
        m_skyWalker.set(spin);
    }
}
