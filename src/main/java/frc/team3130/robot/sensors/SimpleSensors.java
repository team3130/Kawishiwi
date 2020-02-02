package frc.team3130.robot.sensors;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SimpleSensors {

    private DigitalInput input0 = new DigitalInput(0);

    public static boolean get0() {
        return GetInstance().input0.get();
    }

    public static void outputToSmartDashboard() {
        SmartDashboard.putBoolean("DIO-00", get0());
        DriverStation.reportWarning("ZEUS... DIO00="+get0(), false);
    }

    // Instance Handling
    private static SimpleSensors m_pInstance = null;
    public  static SimpleSensors GetInstance() {
        if (m_pInstance == null)
            m_pInstance = new SimpleSensors();
        return m_pInstance;
    }
}
