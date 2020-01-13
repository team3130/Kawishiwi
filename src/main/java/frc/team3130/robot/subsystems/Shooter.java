package frc.team3130.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3130.robot.RobotMap;

public class Shooter implements Subsystem {

    //Create necessary objects
    private static WPI_TalonSRX m_shooterLeft;
    private static WPI_TalonSRX m_shooterRight;
    private static WPI_TalonSRX m_turretAngle;



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
        m_turretAngle = new WPI_TalonSRX(RobotMap.CAN_TURRET);


        m_shooterLeft.configFactoryDefault();
        m_shooterRight.configFactoryDefault();
        m_turretAngle.configFactoryDefault();

        configPIDF(m_turretAngle,
                RobotMap.kTurretP,
                RobotMap.kTurretI,
                RobotMap.kTurretD,
                0.0);

        /**
         * Constructor:
         * Define and configure your defined objects (ie. talons, vars)
         *
         * _talon.configFactoryDefault();
         * resets hardware defaults that could have been configured on talon before
         *
         */

    }
    public synchronized static void setWristRelativeAngle(double angle) {
        mWristState = WristControlState.MOTION_MAGIC;
        wristPeriodicIO.setpoint = RobotMap.kWristTicksPerDeg * angle;
    }

    public synchronized static void setAngle(double angle_deg){
        m_turret.changeControlMode(CANTalon.TalonControlMode.Position);
        // In Position mode, outputValue set is in rotations of the motor
        //DEBUG: System.out.println("Set value:  " + (angle_deg / 360.0) * (164.0 / 34.0) + " -------------");
        m_turret.set((angle_deg / 360.0) * (164.0 / 24.0));
    }

    public static void shooterSpin(double spin) {
        m_shooterLeft.set(spin);
        m_shooterRight.set(spin);
    }
    public static void turnTurret(double turn) {
        m_turretAngle.set(turn);
    }
    public static void configPIDF(WPI_TalonSRX _talon, double kP, double kI, double kD, double kF) {
        _talon.config_kP(0, kP, 0);
        _talon.config_kI(0, kI, 0);
        _talon.config_kD(0, kD, 0);
        _talon.config_kF(0, kF, 0);
    }

    private static void configMotionMagic(WPI_TalonSRX yoskiTalon, int acceleration, int cruiseVelocity){
        yoskiTalon.configMotionCruiseVelocity(cruiseVelocity, 0);
        yoskiTalon.configMotionAcceleration(acceleration, 0);
    }



}
