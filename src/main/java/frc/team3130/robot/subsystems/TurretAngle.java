package frc.team3130.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3130.robot.RobotMap;

/**
 * The TurretAngle subsystem controls the angle the ball is fired. The Turret can only 
 * rotate within 196 degrees (+/- 98) because of mechanical hard stops.
 * 
 * The balls are fed from the hopper to the Turret via the ball elevator, the TurretAngle 
 * subsystem set the angle of the Turret. Then the balls are shot out by the TurretFlywheel.
 * 
 * @see
 * 
 */
public class TurretAngle implements Subsystem {
	private static WPI_TalonSRX m_turret;

  //Instance Handling
  	private static TurretAngle m_pInstance;
  	/**
  	 * A system for getting an instance of this class.
  	 * The function provides a method by which the class is setup as a singleton
  	 * with only a single copy of it existing in memory.
  	 * <p> It will return a reference to the class, which is shared amongst all callers of GetInstance()
  	 * 
  	 * @return the reference to the class referred to in GetInstance. In this case, TurretAngle.
  	 */
  	public static TurretAngle GetInstance()
  	{
  		if(m_pInstance == null) m_pInstance = new TurretAngle();
  		return m_pInstance;
  	}


  	
	private TurretAngle() {
		// The turret has one Talon to control angle.
		m_turret = new WPI_TalonSRX(RobotMap.CAN_TURRET);
		m_turret.setNeutralMode(NeutralMode.Brake);

		m_turret.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

		m_turret.overrideLimitSwitchesEnable(false);
		m_turret.overrideSoftLimitsEnable(false);

		m_turret.enableVoltageCompensation(true);
		m_turret.set(ControlMode.Position,0);
		configPIDF(m_turret,
				RobotMap.kTurretP,
				RobotMap.kTurretI,
				RobotMap.kTurretD,
				0.0);

		/*m_turret.setPID(Constants.kTurretKp, Constants.kTurretKi, Constants.kTurretKd, Constants.kTurretKf,
				Constants.kTurretIZone, Constants.kTurretRampRate, 0);*/
		m_turret.selectProfileSlot(0,0);
		//m_turret.reverseSensor(false);
		//m_turret.reverseOutput(false);
		


		m_turret.clearStickyFaults();
	}

	// Set the desired angle of the turret (and put it into position control
	// mode if it isn't already).
	public synchronized static void setAngle(double angle_deg) {
		// In Position mode, outputValue set is in rotations of the motor 
		System.out.println("Set value:  " + (angle_deg / 360.0) * (164.0 / 34.0) + " -------------");
		m_turret.set(ControlMode.Position, (angle_deg / 360.0) * (164.0 / 24.0) * 4098.0);
	}

	// Manually move the turret (and put it into vbus mode if it isn't already). Input range -1.0 to 1.0


	// Tell the Talon it is at a given position.


	public synchronized static double getAngleDegrees() {
		double tangle = RobotMap.kTurretRotationsPerTick * m_turret.getSelectedSensorPosition() * 2.0 * Math.PI;
		return Math.toDegrees(tangle);
	}

	public synchronized static double getSetpoint() {
		return m_turret.getClosedLoopTarget() * 24.0 / 164.0  * 360;
	}

	private synchronized static double getError() {
		return getAngleDegrees() - getSetpoint();
	}

	// We are "OnTarget" if we are in position mode and close to the setpoint.
	public synchronized static boolean isOnTarget() {
		return (m_turret.getControlMode() == m_turret.getControlMode().Position
				&& Math.abs(getError()) < RobotMap.kTurretOnTargetTolerance);
	}

	/**
	 * @return If the turret is within its mechanical limits and in the right
	 *		 state.
	 */

	public static TalonSRX getMotor(){
		return m_turret;
	}




	public static void outputToSmartDashboard() {
		SmartDashboard.putNumber("turret_error", getError());
		SmartDashboard.putNumber("turret_angle", getAngleDegrees());
		//SmartDashboard.putNumber("turret_setpoint", getSetpoint());
		SmartDashboard.putBoolean("turret_on_target", isOnTarget());
	}

	public static void configPIDF(WPI_TalonSRX _talon, double kP, double kI, double kD, double kF) {
		_talon.config_kP(0, kP, 0);
		_talon.config_kI(0, kI, 0);
		_talon.config_kD(0, kD, 0);
		_talon.config_kF(0, kF, 0);
	}



}
