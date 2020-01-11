package frc.team3130.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.team3130.robot.commands.SpinShooter;
import frc.team3130.robot.commands.IntakeIn;
import frc.team3130.robot.commands.IntakeOut;
import frc.team3130.robot.commands.SpinWheel;


public class OI {
    private class JoystickTrigger extends Trigger {

        private Joystick stick;
        private int axis;
        private double threshold;

        private JoystickTrigger(Joystick stick, int axis) {
            this.stick = stick;
            this.axis = axis;
            threshold = 0.1;
        }

        private JoystickTrigger(Joystick stick, int axis, double threshold) {
            this.stick = stick;
            this.axis = axis;
            this.threshold = threshold;
        }

        @Override
        public boolean get() {
            return stick.getRawAxis(axis) > threshold;
        }

    }

    private class POVTrigger extends Trigger {

        private Joystick stick;
        private int POV;

        public POVTrigger(Joystick stick, int POV) {
            this.stick = stick;
            this.POV = POV;
        }

        @Override
        public boolean get() {
            return stick.getPOV(0) == POV;
        }

    }

    //Instance Handling
    private static OI m_pInstance;

    public static OI GetInstance() {
        if (m_pInstance == null) m_pInstance = new OI();
        return m_pInstance;
    }

    public static double getSkywalker() {
        double spin = 0;
        spin += driverGamepad.getRawAxis(RobotMap.LST_AXS_RTRIGGER);
        spin -= driverGamepad.getRawAxis(RobotMap.LST_AXS_LTRIGGER);
        return spin;
    }

    //Joysticks
    public static Joystick driverGamepad;
    public static Joystick weaponsGamepad;

    /**
     * Definitions for joystick buttons start
     */
    private static JoystickButton spinWheel;
    private static JoystickButton spinShooter;

    private static JoystickButton intakeIn;
    private static JoystickButton intakeOut;

    public void checkTriggers() {
        //Driver
        if (Math.abs(OI.driverGamepad.getRawAxis(RobotMap.LST_AXS_LTRIGGER)) >= RobotMap.kIntakeTriggerDeadband) {

        } else {

        }
        if (Math.abs(OI.driverGamepad.getRawAxis(RobotMap.LST_AXS_RTRIGGER)) >= RobotMap.kIntakeTriggerDeadband) {

        } else {

        }
    }

    //Settings for gamepad
    private OI() {
        driverGamepad = new Joystick(0);
        weaponsGamepad = new Joystick(1);

        spinWheel = new JoystickButton(driverGamepad, RobotMap.LST_BTN_A);

        spinShooter = new JoystickButton(driverGamepad, RobotMap.LST_BTN_X);

        intakeIn = new JoystickButton(driverGamepad, RobotMap.LST_BTN_RBUMPER);
        intakeOut = new JoystickButton(driverGamepad, RobotMap.LST_BTN_LBUMPER);

        intakeIn.whileHeld(new IntakeIn());
        intakeOut.whileHeld(new IntakeOut());

        spinWheel.whileHeld(new SpinWheel());

        spinWheel.whileHeld(new SpinShooter());



    }
}

