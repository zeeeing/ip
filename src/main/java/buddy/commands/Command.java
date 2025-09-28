package buddy.commands;

import buddy.exceptions.BuddyException;
import buddy.storage.Storage;
import buddy.tasks.TaskList;
import buddy.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException;

    public abstract boolean isExit();
}
