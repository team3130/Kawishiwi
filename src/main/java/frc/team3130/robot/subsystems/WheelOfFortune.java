package frc.team3130.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3130.robot.RobotMap;


public class WheelOfFortune implements Subsystem {


    private static WheelOfFortune m_pInstance;

    public static WheelOfFortune GetInstance() {
        if (m_pInstance == null) m_pInstance = new WheelOfFortune();
        return m_pInstance;
    }



    private WheelOfFortune() {

    }

    public static void motorSpin (double spin){

    }
}
