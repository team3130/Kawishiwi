package frc.team3130.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.team3130.robot.commands.*;


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

    public static double getTurret() {
        double turn = 0;
        turn += driverGamepad.getRawAxis(RobotMap.LST_AXS_RTRIGGER);
        turn -= driverGamepad.getRawAxis(RobotMap.LST_AXS_LTRIGGER);
        return turn;
    }

    //Joysticks
    public static Joystick driverGamepad;
    public static Joystick weaponsGamepad;

    /**
     * Definitions for joystick buttons start
     */
    private static JoystickButton aimTurret;


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

        aimTurret = new JoystickButton(driverGamepad, RobotMap.LST_BTN_A);


        aimTurret.whileHeld(new TurnTurret());


    }
}

