package frc.team3130.robot.sensors;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;

/**
 *
 */
public class PressureHDI {
    private I2C i2c;
    private int pressure = 0;
    private Thread interrogator;

    private final int LIDAR_ADDR = 0b1111000;

    public static int get() {
        return GetInstance().getStoredRange();
    }

    // Instance Handling
    private static PressureHDI m_pInstance = null;

    public static PressureHDI GetInstance() {
        if (m_pInstance == null)
            m_pInstance = new PressureHDI();
        return m_pInstance;
    }

    public int getStoredRange() {
        return pressure;
    }

    private class InterrogationLoop implements Runnable {
        public void run() {
            try {
                while (true) {
                    // Use a temporary storage to receive the data from the I2C
                    // device so the main data variable is not locked by this
                    // thread for any significant period of time
                    int tempStorage = getReading();
                    pressure = tempStorage;
                    if (tempStorage < 0) {
                        // Negative value means an error.
                        // Some diagnostic code here maybe?
                        Thread.sleep(3000);
                    }
                    Thread.sleep(20);
                }
            } catch (InterruptedException e) {
                // This should never happen. But if it does we have to close the thread gracefully.
                DriverStation.reportError("Thread " + Thread.currentThread().getName() + " got interrupted", true);
                return;
            }
        }
    }

    private PressureHDI() {
		i2c = new I2C(I2C.Port.kOnboard, LIDAR_ADDR);

        interrogator = new Thread(new InterrogationLoop(), "Pressure sensor interrogator");
        interrogator.start();
	}

	// Distance in mm
	private int getReading() {
		byte[] buffer = new byte[2];
        if( !i2c.readOnly(buffer, 2) ) {
            // From the sensor's doc at
            // https://www.first-sensor.com/cms/upload/appnotes/AN_I2C-Bus_HMI_HDI_HCLA_HCA_SSI_E_11155.pdf
            // pressure = ((int16)byte_msb << 8) | byte_lsb;
            // In Java byte is always signed, so watch out.
            return ((0xFF & buffer[0]) << 8) | (0xFF & buffer[1]);
        }
        else {
            return -1;
        }
	}
}
