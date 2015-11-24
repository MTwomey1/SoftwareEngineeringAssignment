package assignment.command;

/**
 * An abstract class that defines basic behavior all
 * of CommandFactory.
 */
public abstract class AbstractCommandFactory {
	public abstract Command createCommand(CommandType commandType);
	public abstract AbstractCommandFactory getSharedInstance();
}
