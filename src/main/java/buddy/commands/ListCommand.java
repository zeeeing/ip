package buddy.commands;

import buddy.storage.Storage;
import buddy.tasks.TaskList;
import buddy.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listAllTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
