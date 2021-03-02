// RobotBuilder Version: 3.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public class Constants 
{
   /**
    * public static final class DriveConstants {
    *   public static final int kLeftMotor1Port = 0;
    *   public static final int kLeftMotor2Port = 1;
    *   public static final int kRightMotor1Port = 2;
    *   public static final int kRightMotor2Port = 3; 
    * }
    */ 

    public static final class DrivetrainConstants 
    {
        public static final double DEFAULT_DEADBAND = 0.15;
        public static final boolean DEFAULT_FORZA_MODE = false;
        public static final double KF = 0.04759;
        public static final double KP = 0.01461;
        public static final double KI = 0.0;
        public static final double KD = 0.0;
        public static final double CLOSED_LOOP_RAMP = 0.5;
        public static final double MAX_VELOCITY = 21549;
        public static final double DEFAULT_MAX_VELOCITY_PERCENTAGE = 0.5;
        public static final double DEFAULT_MAX_TURNING_SPEED = 0.25;
        public static final double VELOCITY_SLOWDOWN_MODIFIER = 0.5;
        public static final double INVALID_INPUT = -99;
        public static final int LEFT_LEAD_TALON_CAN_ID = 0;
        public static final int LEFT_FOLLOWER_TALON_CAN_ID = 1;
        public static final int RIGHT_LEAD_TALON_CAN_ID = 2;
        public static final int RIGHT_FOLLOWER_TALON_CAN_ID = 3;
        public static final int PID_SLOT_ID = 0;
        public static final int PID_CONFIG_TIMEOUT_MS = 10;
        public static final int CONFIG_FEEDBACKSENSOR_TIMEOUT_MS = 4000;
        public static final double MIN_VELOCITY = 0;
        public static final double MAX_ACCEL = 2000;
        public static final int SMART_MOTION_SLOT = 0;
        public static final double MIN_OUTPUT = -1;
        public static final double ENCODER_CONVERSION_FACTOR = 0.25;
        public static final double MAX_OUTPUT = 1;
    }
}
