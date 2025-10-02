package buddy.tasks;

/**
 * Represents a simple {@link Task} with a description.
 */
public class Todo extends Task {

    /**
     * Creates a todo task with the given description.
     *
     * @param description - Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the todo-specific string representation prefixed with its tag.
     *
     * @return {@link String} representation suitable for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
