package buddy.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(Collection<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public List<Task> getTasks() {
        return tasks;
    }

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
