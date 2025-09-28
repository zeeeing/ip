package buddy.commands;

import buddy.exceptions.InvalidIndexException;
import buddy.storage.Storage;
import buddy.tasks.Task;
import buddy.tasks.TaskList;
import buddy.ui.Ui;

public class DeleteCommand extends Command {
    private final int index; // 0-based index

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidIndexException("Invalid task number: " + (index + 1));
        }
        Task removed = tasks.delete(index);
        ui.printTaskDeletedSuccessMessage(removed, tasks.size());
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
