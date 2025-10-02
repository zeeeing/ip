package buddy.commands;

import buddy.storage.Storage;
import buddy.tasks.TaskList;
import buddy.ui.Ui;

/**
 * Lists all tasks currently tracked by Buddy.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listAllTasks(tasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
