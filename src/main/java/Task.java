public class Task {
    private final String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void mark() {
        this.isCompleted = true;
    }

    public void unmark() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        String status = isCompleted ? "[X] " : "[ ] ";
        return status + description;
    }
}
