package buddy.commands;

import buddy.exceptions.BuddyException;
import buddy.storage.Storage;
import buddy.tasks.TaskList;
import buddy.ui.Ui;

/**
 * Represents a user command that can be executed against the task list.
 */
public abstract class Command {
    /**
     * Executes this command using the provided context.
     *
     * @param tasks tasks currently tracked by Buddy
     * @param ui user interface through which messages are shown
     * @param storage persistent storage backing the task list
     * @throws BuddyException if the command cannot be completed successfully
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException;

    /**
     * Indicates whether this command requests the program to terminate.
     *
     * @return true if Buddy should exit after this command runs, false otherwise
     */
    public abstract boolean isExit();
}
