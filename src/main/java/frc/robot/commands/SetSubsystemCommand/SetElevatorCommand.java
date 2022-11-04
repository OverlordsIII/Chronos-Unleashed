package frc.robot.commands.SetSubsystemCommand;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class SetElevatorCommand extends CommandBase {

    private final ElevatorSubsystem m_subsystem;

    private final ElevatorSubsystem.ElevatorSubsystemMode mode;

    private final Timer m_timer = new Timer();

    public SetElevatorCommand(ElevatorSubsystem.ElevatorSubsystemMode powerMode) {
        m_subsystem = ElevatorSubsystem.getInstance();
        this.mode = powerMode;
    }

    /**
     * The initial subroutine of a command. Called once when the command is initially scheduled.
     */
    @Override
    public void initialize() {
        m_timer.reset();
        m_timer.start();
    }

    /**
     * The main body of a command. Called repeatedly while the command is scheduled.
     */
    @Override
    public void execute() {
        m_subsystem.setMotorMode(mode);
    }

    /**
     * Whether the command has finished. Once a command finishes, the scheduler will call its end()
     * method and un-schedule it.
     *
     * @return whether the command has finished.
     */
    @Override
    public boolean isFinished() {
        return m_timer.hasElapsed(5);
    }
}
