package buddy.commands;

import buddy.storage.Storage;
import buddy.tasks.TaskList;
import buddy.ui.Ui;

/**
 * Terminates the Buddy application gracefully.
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exitProgram();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
