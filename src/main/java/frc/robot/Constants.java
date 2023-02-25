// RobotBuilder Version: 4.0
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
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be
 * declared globally (i.e. public static). Do not put anything functional in
 * this class.
 *
 * 
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public class Constants {
    public static final class DrivetrainConstants {
        // PID Constants
        /*
         * To tune the PID:
         * 1. Using Pheonix tuner, set motors to factory default
         * 2. Set the velocity to 100%, this is the MAX_ENCODER_VELOCITY,
         * use the slower of the two motor systems!
         * 3. Calculate KF by hand using the KF equation below
         * 4. Increase P until the system oscillates by a measureable time
         * 5. Measure the period of the oscillation in seconds
         * 6. The P gain used to get this measureable oscillation is KU, enter KU
         * 7. The measured period of the oscillation, in seconds, is TU, enter TU
         * Done.
         */

        // Choose the slower motor speed max, in this case the right motor
        // MISC Constants
        // kinematics - driveto - drivetrain
    }



    public static final class AutonomousCommandConstants {
        // drive to
        public static final double TARGET_WITHIN_RANGE_FEET = DrivetrainConstants.TRACK_WIDTH_FEET / 4.0d; // quarter of
                                                                                                           // trackwidth
        // Positions relative to location of driver station
        public static enum StartPositions {
            // robot container
            INVALID,
            // robot container - sequential auto command
            BLUE_LEFT,
            BLUE_MIDDLE,
            BLUE_RIGHT,
            // sequential auto command
            RED_LEFT,
            // robotc container - sequential auto command
            RED_MIDDLE,
            RED_RIGHT
        }
    }
}
