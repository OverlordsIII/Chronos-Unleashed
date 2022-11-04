package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.drivers.LazyTalonFX;
import frc.lib.drivers.TalonFXFactory;

public class ElevatorSubsystem extends SubsystemBase {

    private final LazyTalonFX m_parentMotor;

    private final LazyTalonFX m_childMotor;

    private ElevatorSubsystemMode currentMode;
    private static ElevatorSubsystem instance = null;

    public ElevatorSubsystem() {
        this.m_parentMotor = TalonFXFactory.createDefaultFalcon("Parent Motor" ,1000); // using random num for right now
        this.m_childMotor = TalonFXFactory.createSlaveFalcon("Child Motor", 1001, 1000); //again random num
        m_childMotor.setMaster(m_parentMotor);
    }

    public void setMotorMode(ElevatorSubsystemMode mode) {
        this.m_parentMotor.set(ControlMode.Velocity, mode.power);
        this.currentMode = mode;
    }

    public ElevatorSubsystemMode getCurrentMode() {
        return currentMode;
    }

    public static ElevatorSubsystem getInstance() {
        if (instance == null) {
            instance = new ElevatorSubsystem();
        }

        return instance;
    }

    /**
     * Gets the name of this Subsystem.
     *
     * @return Name
     */
    @Override
    public String getName() {
        return "Elevator Subsystem";
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Motor Velocity", this.currentMode.power);
    }

    public enum ElevatorSubsystemMode {
        UP(5000),
        OFF(0),
        DOWN(-5000);
        
        public final double power;

        ElevatorSubsystemMode(double power) {
            this.power = power;
        }
    }
}
