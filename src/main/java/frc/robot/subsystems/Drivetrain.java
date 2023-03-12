// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.classes.Kinematics;
import frc.robot.classes.Position2D;
import frc.robot.classes.SPIKE293Utils;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SerialPort;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;

import org.littletonrobotics.junction.Logger;

public class Drivetrain extends SubsystemBase {
    private WPI_TalonFX leftTalonLead;
    private WPI_TalonFX rightTalonLead;
    private WPI_TalonFX leftTalonFollower;
    private WPI_TalonFX rightTalonFollower;
    private AHRS navX;
    private Kinematics m_kinematics;

    public static final double VELOCITY_KF = 0.046d;
    public static final double VELOCITY_KP = 0.03d;
    public static final double VELOCITY_KD = 0.06d;
    public static final double POSITION_KF = 0.0d;
    public static final double POSITION_KP = 0.029d;
    public static final double POSITION_KI = 0.0004d;
    public static final double VELOCITY_KI = 0.0d;
    public static final double POSITION_KD = 0.29d;
    public static final double CLOSED_LOOP_RAMP = 0.5;
    public static final int LEFT_LEAD_TALON_CAN_ID = 1;
    public static final int LEFT_FOLLOWER_TALON_CAN_ID = 3;
    public static final int RIGHT_LEAD_TALON_CAN_ID = 0;
    public static final int RIGHT_FOLLOWER_TALON_CAN_ID = 2;
    public static final int VELOCITY_PID_SLOT_ID = 0;
    public static final double MOTOR_NEUTRAL_DEADBAND = 0.001d;
    public static final int POSITION_PID_SLOT_ID = 1;
    public static final int PID_CONFIG_TIMEOUT_MS = 10;
    public static final int CONFIG_FEEDBACKSENSOR_TIMEOUT_MS = 4000;
    public static final int MAX_VELOCITY = 20580;

    public static final boolean USE_NAVX_HEADING = false;

    public static final double TRACK_WIDTH_FEET = 27.5d / 12.0d; // Track width is 27.5 inches

    public Drivetrain(Kinematics kinematics) {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        leftTalonLead = new WPI_TalonFX(LEFT_LEAD_TALON_CAN_ID);
        rightTalonLead = new WPI_TalonFX(RIGHT_LEAD_TALON_CAN_ID);
        leftTalonFollower = new WPI_TalonFX(LEFT_FOLLOWER_TALON_CAN_ID);
        rightTalonFollower = new WPI_TalonFX(RIGHT_FOLLOWER_TALON_CAN_ID);

        leftTalonLead.clearStickyFaults();
        rightTalonLead.clearStickyFaults();

        // Set facotry defaults for onboard PID
        leftTalonLead.configFactoryDefault();
        rightTalonLead.configFactoryDefault();

        leftTalonLead.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0,
                CONFIG_FEEDBACKSENSOR_TIMEOUT_MS);
        rightTalonLead.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0,
                CONFIG_FEEDBACKSENSOR_TIMEOUT_MS);

        leftTalonLead.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 1,
                CONFIG_FEEDBACKSENSOR_TIMEOUT_MS);
        rightTalonLead.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 1,
                CONFIG_FEEDBACKSENSOR_TIMEOUT_MS);

        leftTalonLead.setInverted(false);
        leftTalonLead.setSensorPhase(false);
        leftTalonFollower.setInverted(InvertType.FollowMaster);
        rightTalonLead.setInverted(true);
        rightTalonLead.setSensorPhase(true);
        rightTalonFollower.setInverted(InvertType.FollowMaster);

        // Configure Velocity PID
        leftTalonLead.config_kF(VELOCITY_PID_SLOT_ID, VELOCITY_KF, PID_CONFIG_TIMEOUT_MS);
        leftTalonLead.config_kP(VELOCITY_PID_SLOT_ID, VELOCITY_KP, PID_CONFIG_TIMEOUT_MS);
        leftTalonLead.config_kI(VELOCITY_PID_SLOT_ID, VELOCITY_KI, PID_CONFIG_TIMEOUT_MS);
        leftTalonLead.config_kD(VELOCITY_PID_SLOT_ID, VELOCITY_KD, PID_CONFIG_TIMEOUT_MS);
        leftTalonLead.configClosedloopRamp(CLOSED_LOOP_RAMP);
        leftTalonLead.configOpenloopRamp(0.2);

        rightTalonLead.config_kF(VELOCITY_PID_SLOT_ID, VELOCITY_KF, PID_CONFIG_TIMEOUT_MS);
        rightTalonLead.config_kP(VELOCITY_PID_SLOT_ID, VELOCITY_KP, PID_CONFIG_TIMEOUT_MS);
        rightTalonLead.config_kI(VELOCITY_PID_SLOT_ID, VELOCITY_KI, PID_CONFIG_TIMEOUT_MS);
        rightTalonLead.config_kD(VELOCITY_PID_SLOT_ID, VELOCITY_KD, PID_CONFIG_TIMEOUT_MS);
        rightTalonLead.configClosedloopRamp(CLOSED_LOOP_RAMP);
        rightTalonLead.configOpenloopRamp(0.2);

        // Configure Position PID
        leftTalonLead.config_kF(POSITION_PID_SLOT_ID, POSITION_KF, PID_CONFIG_TIMEOUT_MS);
        leftTalonLead.config_kP(POSITION_PID_SLOT_ID, POSITION_KP, PID_CONFIG_TIMEOUT_MS);
        leftTalonLead.config_kI(POSITION_PID_SLOT_ID, POSITION_KI, PID_CONFIG_TIMEOUT_MS);
        leftTalonLead.config_IntegralZone(POSITION_PID_SLOT_ID, 1000);
        leftTalonLead.config_kD(POSITION_PID_SLOT_ID, POSITION_KD, PID_CONFIG_TIMEOUT_MS);

        rightTalonLead.config_kF(POSITION_PID_SLOT_ID, POSITION_KF, PID_CONFIG_TIMEOUT_MS);
        rightTalonLead.config_kP(POSITION_PID_SLOT_ID, POSITION_KP, PID_CONFIG_TIMEOUT_MS);
        rightTalonLead.config_kI(POSITION_PID_SLOT_ID, POSITION_KI, PID_CONFIG_TIMEOUT_MS);
        rightTalonLead.config_IntegralZone(POSITION_PID_SLOT_ID, 1000);
        rightTalonLead.config_kD(POSITION_PID_SLOT_ID, POSITION_KD, PID_CONFIG_TIMEOUT_MS);

        rightTalonLead.setNeutralMode(NeutralMode.Coast);
        rightTalonFollower.setNeutralMode(NeutralMode.Coast);
        leftTalonLead.setNeutralMode(NeutralMode.Coast);
        leftTalonFollower.setNeutralMode(NeutralMode.Coast);

        rightTalonLead.configNeutralDeadband(MOTOR_NEUTRAL_DEADBAND);
        leftTalonLead.configNeutralDeadband(MOTOR_NEUTRAL_DEADBAND);

        navX = new AHRS(SerialPort.Port.kMXP);

        m_kinematics = kinematics;

        leftTalonFollower.follow(leftTalonLead);
        rightTalonFollower.follow(rightTalonLead);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // Put code here to be run every loop
        // Run Kinematics
        if (USE_NAVX_HEADING) {
            // use the NAVX for heading
            double headingInRadians = Math.toRadians(getGyroHeadingDegrees());
            m_kinematics.calculatePosition(getLeftEncoderPosition(), getRightEncoderPosition(), headingInRadians);
        } else {
            // Use the encoder information for heading
            m_kinematics.calculatePosition(getLeftEncoderPosition(), getRightEncoderPosition());
        }

        // Get current pose from Kinematics
        Position2D currentPose = m_kinematics.getPose();

        // Log Position w/ Pose2d class
        Pose2d currentPose2d = new Pose2d(currentPose.getX(), currentPose.getY(),
                Rotation2d.fromDegrees(currentPose.getHeadingDegrees()));
        Logger.getInstance().recordOutput("odometry", currentPose2d);

        // Push robot info to Dashboard
        SmartDashboard.putNumber("Kinematics X (Feet)", currentPose.getX());
        SmartDashboard.putNumber("Kinematics Y (Feet)", currentPose.getY());
        SmartDashboard.putNumber("Kinematics Heading (degrees)", currentPose.getHeadingDegrees());

        Hashtable<String, Double> doubleVals = new Hashtable<String, Double>() {
            {
                put("Left Encoder Velocity (Ft per S)", getLeftEncoderVelocity());
                put("Left Encoder Position (Ft)", getLeftEncoderPosition());
                put("Right Encoder Veloctiy (Ft per S)", getRightEncoderVelocity());
                put("Right Encoder Position (Ft)", getRightEncoderPosition());
                put("Raw Left Encoder", leftTalonLead.getSelectedSensorPosition(0));
                put("Raw Right Encoder", rightTalonLead.getSelectedSensorPosition(0));

                put("Robot Heading (degrees)", getGyroHeadingDegrees());
                put("NavX X Accel", (double) navX.getWorldLinearAccelX());
                put("NavX Y Accel", (double) navX.getWorldLinearAccelY());
                put("NavX Z Accel", (double) navX.getWorldLinearAccelZ());
                put("NavX Yaw", getGyroYawDegrees());
                put("NavX Angle", getGyroHeadingDegrees());
                put("NavX Fused Heading", getGyroFusedHeadingDegrees());
                put("NavX TurnRate dg per s", navX.getRate());

                put("Left Motor Position Error", leftTalonLead.getClosedLoopError(0));
                put("Right Motor Position Error", rightTalonLead.getClosedLoopError(0));
            }
        };
        Enumeration<String> doubleValsKeys = doubleVals.keys();
        for (String key : Collections.list(doubleValsKeys)) {
            double val = doubleVals.get(key);
            SmartDashboard.putNumber(key, val);
        }
    }
    
    public void percentDrive(double leftPercentage, double rightPercentage) {  
        leftTalonLead.set(ControlMode.PercentOutput, leftPercentage);
        rightTalonLead.set(ControlMode.PercentOutput, rightPercentage);
    }

    public void arcadeDrive(double velocity, double turning) {
        // Convert turning and speed to left right encoder velocity
        double leftMotorOutput;
        double rightMotorOutput;

        double maxInput = Math.copySign(Math.max(Math.abs(velocity), Math.abs(turning)), velocity);
        if (velocity >= 0.0) {
            // First quadrant, else second quadrant
            if (turning >= 0.0) {
                leftMotorOutput = maxInput;
                rightMotorOutput = velocity - turning;
            } else {
                leftMotorOutput = velocity + turning;
                rightMotorOutput = maxInput;
            }
        } else {
            // Third quadrant, else fourth quadrant
            if (turning >= 0.0) {
                leftMotorOutput = velocity + turning;
                rightMotorOutput = maxInput;
            } else {
                leftMotorOutput = maxInput;
                rightMotorOutput = velocity - turning;
            }
        }

        // Send to motors
        velocityDrive(leftMotorOutput * MAX_VELOCITY, rightMotorOutput * MAX_VELOCITY);
    }

    public void stop() {
        leftTalonLead.set(0);
        rightTalonLead.set(0);
    }

    // Sets the motors to encoder units per desisec (100ms), uses the onboard motor
    // PID
    public void velocityDrive(double vL, double vR) {
        SmartDashboard.putNumber("Set Velocity Left (Encoder units/100ms)", vL);
        SmartDashboard.putNumber("Set Velocity Right (Encoder units/100ms)", vR);
        leftTalonLead.selectProfileSlot(VELOCITY_PID_SLOT_ID, 0);
        rightTalonLead.selectProfileSlot(VELOCITY_PID_SLOT_ID, 0);
        leftTalonLead.set(TalonFXControlMode.Velocity, vL);
        rightTalonLead.set(TalonFXControlMode.Velocity, vR);
    }

    public void initAutonomous(Position2D startingPose) {
        // reset encoders
        zeroDriveTrainEncoders();

        m_kinematics.setPose(startingPose);
        // Reset Gyro
        setupGyro(navX, startingPose.getHeadingDegrees());
    }

    /**
     * returns left encoder position
     * 
     * @return left encoder position
     */
    public double getLeftEncoderPosition() {
        // Returns the number of steps, multiply by edges per step to get EPR, divided
        // by the gearbox ratio
        return SPIKE293Utils.controllerUnitsToFeet(leftTalonLead.getSelectedSensorPosition(0));
    }

    /**
     * returns right encoder position
     * 
     * @return right encoder position
     */
    public double getRightEncoderPosition() {
        // Returns the number of steps, multiply by edges per step to get EPR, divided
        // by the gearbox ratio
        return SPIKE293Utils.controllerUnitsToFeet(rightTalonLead.getSelectedSensorPosition(0));
    }

    /**
     * returns left encoder Velocity in ft/s
     * 
     * @return left encoder Velocity in ft/s
     */
    public double getLeftEncoderVelocity() {
        // Returns the velocity of encoder by claculating the velocity from encoder
        // units of click/100ms to ft/s
        return SPIKE293Utils.controllerVelocityToFeetPerSec(leftTalonLead.getSelectedSensorVelocity());
    }

    /**
     * returns right encoder Velocity in ft/s
     * 
     * @return right encoder Velocity in ft/s
     */
    public double getRightEncoderVelocity() {
        // Returns the velocity of encoder by claculating the velocity from encoder
        // units of click/100ms to ft/s
        return SPIKE293Utils.controllerVelocityToFeetPerSec(rightTalonLead.getSelectedSensorVelocity());
    }

    /**
     * returns robot Velocity in ft/s
     * 
     * @return robot Velocity in ft/s
     */
    public double getRobotVelocity() {
        // Returns the velocity of the robot by taking the averga of the velcity on both
        // sides of the robor
        return (getLeftEncoderVelocity() + getRightEncoderVelocity()) / 2.0d;
    }

    /**
     * resets the drive train encoders to 0
     */
    private void zeroDriveTrainEncoders() {
        leftTalonLead.setSelectedSensorPosition(0);
        rightTalonLead.setSelectedSensorPosition(0);
    }

    public double getGyroFusedHeadingDegrees() {
        return (navX.getFusedHeading() * -1.0d);
    }

    public double getGyroYawDegrees() {
        return (navX.getYaw() * -1.0d);
    }

    public double getGyroHeadingDegrees() {
        return (navX.getAngle() * -1.0d);
    }

    public void setupGyro(AHRS gyro, double startingAngleDegrees) {

        System.out.println("Calibrating gyroscope.");

        gyro.enableBoardlevelYawReset(true);
        gyro.reset();
        gyro.calibrate();

        // Wait for gyro to calibrate ~1 - 10 seconds
        while (gyro.isCalibrating()) {
        }

        // Set's the starting angle to the given angle
        gyro.setAngleAdjustment(startingAngleDegrees);

        System.out.println("Calibrating gyroscope done.");
    }

    public void resetKinematics() {
        setupGyro(navX, 0.0d);
        zeroDriveTrainEncoders();
    }

    public void resetGyro(double headingDegrees) {
        setupGyro(navX, headingDegrees);
    }

    // rotates robot according to give degress using arc length formula
    public void rotateDegrees(double angle) {
        double radians = Math.toRadians(angle);
        double arcLength = (radians * (TRACK_WIDTH_FEET / 2.0));
        double encoderTicks = SPIKE293Utils.feetToControllerUnits(arcLength);
        double leftEncoderPosition = leftTalonLead.getSelectedSensorPosition(0);
        double rightEncoderPosition = rightTalonLead.getSelectedSensorPosition(0);
        positionControl(leftEncoderPosition - encoderTicks, rightEncoderPosition + encoderTicks);
    }

    // sets left and right talons to given parameters
    public void positionControl(double posL, double posR) {
        leftTalonLead.selectProfileSlot(POSITION_PID_SLOT_ID, 0);
        rightTalonLead.selectProfileSlot(POSITION_PID_SLOT_ID, 0);

        leftTalonLead.set(ControlMode.Position, posL);
        rightTalonLead.set(ControlMode.Position, posR);
    }

    public double getMotorError() {
        return leftTalonLead.getClosedLoopError(0);
    }
}
