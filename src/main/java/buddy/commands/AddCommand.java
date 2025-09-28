package buddy.commands;

import buddy.storage.Storage;
import buddy.tasks.Task;
import buddy.tasks.TaskList;
import buddy.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.printTaskAddedSuccessMessage(task, tasks.size());
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
