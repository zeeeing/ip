package buddy.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final String fromRaw;
    private final String toRaw;

    public Event(String description, String fromRaw, String toRaw) {
        super(description);
        this.fromDate = null;
        this.toDate = null;
        this.fromRaw = fromRaw;
        this.toRaw = toRaw;
    }

    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromRaw = null;
        this.toRaw = null;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public String getFromRaw() {
        return fromRaw;
    }

    public String getToRaw() {
        return toRaw;
    }

    private String formatDisplay(LocalDate date, String raw) {
        if (date != null) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return date.format(dateFormat);
        }
        return raw;
    }

    @Override
    public String toString() {
        String fromDisplay = formatDisplay(fromDate, fromRaw);
        String toDisplay = formatDisplay(toDate, toRaw);
        return "[E]" + super.toString() + " (from: " + fromDisplay + " to: " + toDisplay + ")";
    }
}
