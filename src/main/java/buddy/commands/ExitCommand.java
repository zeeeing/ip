package buddy.commands;

import buddy.storage.Storage;
import buddy.tasks.TaskList;
import buddy.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exitProgram();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
