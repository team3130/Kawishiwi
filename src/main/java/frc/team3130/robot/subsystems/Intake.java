package frc.team3130.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3130.robot.RobotMap;

public class Intake implements Subsystem {

    //Create necessary objects
    private static WPI_TalonSRX m_intakeMotorMaster;
    private static WPI_TalonSRX m_intakeMotorSlave;

    //Create and define all standard data types needed

    /**
     * The Singleton instance of this Intake. External classes should
     * use the {@link #getInstance()} method to get the instance.
     */
    private final static Intake INSTANCE = new Intake();

    /**
     * Returns the Singleton instance of this ExampleSubsystem. This static method
     * should be used -- {@code ExampleSubsystem.getInstance();} -- by external
     * classes, rather than the constructor to get the instance of this class.
     */
    public static Intake getInstance() {
        return INSTANCE;
    }

    private Intake() {
        m_intakeMotorMaster = new WPI_TalonSRX(RobotMap.CAN_INTAKE1);
        m_intakeMotorSlave = new WPI_TalonSRX(RobotMap.CAN_INTAKE2);

        m_intakeMotorMaster.configFactoryDefault();
        m_intakeMotorSlave.configFactoryDefault();

        m_intakeMotorMaster.setNeutralMode(NeutralMode.Coast);
        m_intakeMotorSlave.setNeutralMode(NeutralMode.Coast);

        m_intakeMotorSlave.set(ControlMode.Follower, RobotMap.CAN_INTAKE1);

        m_intakeMotorMaster.overrideLimitSwitchesEnable(false);
    }

    public static void runIntake(double speed){
        m_intakeMotorMaster.set(speed);
    }

}

