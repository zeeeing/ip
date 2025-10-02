package buddy.tasks;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    private final String description;
    private boolean isCompleted;

    /**
     * Creates a task with the given description. The task is initially not
     * completed.
     *
     * @param description - Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Returns the textual description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks whether the task is completed.
     *
     * @return <code>true</code> if the task is completed, <code>false</code>
     * otherwise.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Returns the display-ready string representation of the task.
     *
     * @return {@link String} combining completion status and description.
     */
    @Override
    public String toString() {
        String status = isCompleted ? "[X] " : "[ ] ";
        return status + description;
    }
}
