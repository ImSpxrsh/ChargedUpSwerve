// RobotBuilder Version: 3.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import static frc.robot.Constants.LauncherConstants.*;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class Launcher extends SubsystemBase 
{
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANT
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonFX m_launcherMotor;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    private DoubleSolenoid m_shooterPiston;
    private double m_targetRpm;
    
    /**
    *
    */
    public Launcher() 
    {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        m_launcherMotor = new WPI_TalonFX(6); 
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        m_shooterPiston = new DoubleSolenoid(PISTON_MODULE_NUM, PISTON_FORWARD_CHANNEL, PISTON_REVERSE_CHANNEL);

        m_launcherMotor.clearStickyFaults();
        m_launcherMotor.configFactoryDefault();
        m_launcherMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        m_launcherMotor.config_kF(PID_SLOT_ID, KF, PID_CONFIG_TIMEOUT_MS);
        m_launcherMotor.config_kP(PID_SLOT_ID, KP, PID_CONFIG_TIMEOUT_MS);
        m_launcherMotor.config_kI(PID_SLOT_ID, KI, PID_CONFIG_TIMEOUT_MS);
        m_launcherMotor.config_kD(PID_SLOT_ID, KD, PID_CONFIG_TIMEOUT_MS);
        m_launcherMotor.setInverted(true);
        m_launcherMotor.configClosedloopRamp(CLOSED_LOOP_RAMPRATE);

        m_targetRpm = 0.0d;
        SmartDashboard.putNumber("Launcher Target RPM", m_targetRpm);
    }

    @Override
    public void periodic() 
    {
        //Get target RPM from SmartDashboard
        m_targetRpm = SmartDashboard.getNumber("Launcher Target RPM", 0.0d);
        //Set the launcher wheel to the target RPM
        setRpm(m_targetRpm);
    }
    
    @Override
    public void simulationPeriodic() 
    {
        // This method will be called once per scheduler run when in simulation
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    //Converts RPMs to encoder units per desiseconds (100ms)
    private double convertRPMtoVelocity(double rpm) 
    {   
        return (rpm * GEAR_RATIO * ENCODER_UNITS_PER_REVOLUTION) / MINUTES_TO_DECISECONDS;
    }

    //Converys Encoder units per desiseconds (100ms) to RPMs
    private double convertVelocityToRPM(double velocity) 
    {
        return (velocity * MINUTES_TO_DECISECONDS) / (ENCODER_UNITS_PER_REVOLUTION * GEAR_RATIO);
    }

    // Sets launcher RMP to set RPM , used to turn on the launcher
    public void setRpm(double rpm) 
    {
        double velocity;

        //Save new target RPM
        m_targetRpm = rpm;

        //Push new RPM target to Dashboad
        SmartDashboard.putNumber("Launcher Target RPM", m_targetRpm);

        //Convert RPM to velocity
        velocity = convertRPMtoVelocity(m_targetRpm);

        //Set new velocity
        m_launcherMotor.set(ControlMode.Velocity, velocity);
    }

    //Turns off the launcher wheel
    public void stop()
    {
        m_targetRpm = 0.0;
        SmartDashboard.putNumber("Launcher Target RPM", m_targetRpm);
        m_launcherMotor.set(ControlMode.Current, 0);
    }

    //Gets the current RPMs
    public double getCurrentRpm()
    {
        return convertVelocityToRPM(m_launcherMotor.getSelectedSensorVelocity(0));
    }

    //Determines if the launcher wheel is up to speed
    public boolean isReady() 
    {   
        boolean isReady = false;
        double currentRPM = getCurrentRpm();

        //Don't check if launcher is ready if the target RPM is 0!
        if((0 != m_targetRpm) 
            && ((Math.abs(currentRPM - m_targetRpm)) <= (TARGET_RPM_READY_THRESHOLD)))
        {
            isReady = true;
        }

        return isReady;
    }
    
    //Extends the shooter piston
    public void extendShooterPiston()
    {
        m_shooterPiston.set(Value.kForward);
    }
    
    //Retracts the shooter piston
    public void retractShooterPiston()
    {
        m_shooterPiston.set(Value.kReverse);
    }
}
