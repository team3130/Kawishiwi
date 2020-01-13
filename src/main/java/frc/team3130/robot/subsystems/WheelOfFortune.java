package frc.team3130.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import frc.team3130.robot.RobotMap;
import frc.team3130.robot.commands.SpinWheel;

import java.util.HashMap;
import java.util.Map;

public class WheelOfFortune implements Subsystem {

    //Create necessary objects
    private static ColorSensorV3 m_colorSensor;
    private static ColorMatch m_colorMatcher;
    private static WPI_TalonSRX m_spinWheel;

    //Create and define all standard data types needed
    private final I2C.Port i2cPort = I2C.Port.kOnboard;

    //TODO: Switch this to HSV
    private static final Color kCyanTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private static final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private static final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private static final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
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
        m_colorSensor = new ColorSensorV3(i2cPort);
        m_colorMatcher = new ColorMatch();

        m_colorMatcher.addColorMatch(kCyanTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);

        m_spinWheel = new WPI_TalonSRX(RobotMap.CAN_WHEELOFFORTUNE);
        m_spinWheel.configFactoryDefault();
    }

    public static int proximityCheck() {
        return m_colorSensor.getProximity();
    }


    /**
     * Run the color match algorithm on our detected color
     *
     * @return String name of the most likely color
     */
    public static String detectColor() {
        Color detectedColor = m_colorSensor.getColor();

        String colorString;
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if (match.color == kCyanTarget) {
            colorString = "Cyan";
        } else if (match.color == kRedTarget) {
            colorString = "Red";
        } else if (match.color == kGreenTarget) {
            colorString = "Green";
        } else if (match.color == kYellowTarget) {
            colorString = "Yellow";
        } else {
            colorString = "Unknown";
        }

        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putNumber("Confidence", match.confidence);
        SmartDashboard.putString("Detected Color", colorString);
        SmartDashboard.putNumber("Proximity", proximityCheck());

        return colorString;
    }

    //This is the Map for converting the fieldColor into targetColor, which can be used to clear a lot of confusion while making the algorithm
    Map<String,String> fieldToTargetColorMap = new HashMap<String,String>();
    fieldToTargetColorMap.put("Blue","Red");
    fieldToTargetColorMap.put("Green","Yellow");
    fieldToTargetColorMap.put("Red","Blue");
    fieldToTargetColorMap.put("Yellow","Green");







    public static void motorSpin(double spin) {
        m_spinWheel.set(spin);
    }

    public static void outputToSmartDashboard() {

    }
}
