package buddy.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific deadline.
 */
public class Deadline extends Task {

    private final LocalDate byDate;
    private final String byRaw;

    /**
     * Creates a deadline task with a structured {@link LocalDate}.
     *
     * @param description - Description of the deadline.
     * @param date - {@link LocalDate} by which the task should be completed.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.byDate = date;
        this.byRaw = null;
    }

    /**
     * Creates a deadline task with a raw textual date representation.
     *
     * @param description - Description of the deadline.
     * @param byRaw - Raw text describing the deadline.
     */
    public Deadline(String description, String byRaw) {
        super(description);
        this.byDate = null;
        this.byRaw = byRaw;
    }

    /**
     * Returns the parsed deadline date, if available.
     *
     * @return Parsed deadline date or {@code null} when only text was provided.
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * Returns the raw deadline text, if available.
     *
     * @return Raw deadline text or {@code null} when a structured date was
     * provided.
     */
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

    /**
     * Returns the deadline-specific string representation including the due
     * date.
     *
     * @return {@link String} representation suitable for display.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDisplay() + ")";
    }
}
