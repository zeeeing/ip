package buddy.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDate byDate;
    private final String byRaw;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.byDate = date;
        this.byRaw = null;
    }

    public Deadline(String description, String byRaw) {
        super(description);
        this.byDate = null;
        this.byRaw = byRaw;
    }

    public LocalDate getByDate() {
        return byDate;
    }

    public String getByRaw() {
        return byRaw;
    }

    private String formatDisplay() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        if (byDate != null) {
            return byDate.format(dateFormat);
        }
        return byRaw;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDisplay() + ")";
    }
}
