package frc.team3130.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3130.robot.RobotMap;



public class Climber implements Subsystem {


    private static Climber m_pInstance;

    public static Climber GetInstance() {
        if (m_pInstance == null) m_pInstance = new Climber();
        return m_pInstance;
    }

    private static WPI_TalonSRX m_skyWalker;

    private Climber() {
        m_skyWalker = new WPI_TalonSRX(RobotMap.CAN_LEFTMOTORFRONT);
        m_skyWalker.configFactoryDefault();
    }
    public static void motorSpin (double spin){
        spin=0.7;
        m_skyWalker.set(spin);
    }
    public static void motorStopSpin (double spin){
        spin=0;
        m_skyWalker.set(spin);
    }
    public static void reset() {
        m_skyWalker.setSelectedSensorPosition(0);

    }
}
