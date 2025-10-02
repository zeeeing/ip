package buddy.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Encapsulates the list of tasks tracked by Buddy and provides {@link List}
 * operations.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list containing the provided tasks.
     *
     * @param tasks - Tasks to instantiate the list with.
     */
    public TaskList(Collection<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index - Index (zero-based) of the task to remove.
     * @return The removed task.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index - Index (zero-based) of the task to retrieve.
     * @return Task located at the given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks currently tracked.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks whether the list has no tasks.
     *
     * @return <code>true</code> if the list is empty, <code>false</code>
     * otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Provides access to the underlying list of tasks for storage operations.
     *
     * @return {@link List} storing the tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks whose descriptions contain the given keyword,
     * case-insensitively.
     *
     * @param keyword - Keyword to search for.
     * @return {@link List} of matching tasks, possibly empty.
     */
    public List<Task> find(String keyword) {
        ArrayList<Task> results = new ArrayList<>();

        // invalid keyword check
        if (keyword == null || keyword.isEmpty()) {
            return results;
        }

        String lower = keyword.toLowerCase();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(lower)) {
                results.add(task);
            }
        }
        return results;
    }
}
