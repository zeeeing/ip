package buddy.commands;

import buddy.exceptions.InvalidIndexException;
import buddy.storage.Storage;
import buddy.tasks.Task;
import buddy.tasks.TaskList;
import buddy.ui.Ui;

/**
 * Marks a task as not completed based on a zero-based index and saves the change.
 */
public class UnmarkCommand extends Command {
    private final int index; // 0-based index

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidIndexException("Invalid task number: " + (index + 1));
        }
        Task t = tasks.get(index);
        t.unmark();
        ui.printTaskUnmarkedSuccessMessage(t);
        storage.save(tasks.getTasks());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
