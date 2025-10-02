package buddy.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event that spans a start and end date.
 */
public class Event extends Task {

    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final String fromRaw;
    private final String toRaw;

    /**
     * Creates an event using raw textual representations of the start and end.
     *
     * @param description - Description of the event.
     * @param fromRaw - Raw text describing when the event starts.
     * @param toRaw - Raw text describing when the event ends.
     */
    public Event(String description, String fromRaw, String toRaw) {
        super(description);
        this.fromDate = null;
        this.toDate = null;
        this.fromRaw = fromRaw;
        this.toRaw = toRaw;
    }

    /**
     * Creates an event using structured {@link LocalDate} values.
     *
     * @param description - Description of the event.
     * @param fromDate - Start {@link LocalDate} of the event.
     * @param toDate - End {@link LocalDate} of the event.
     */
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromRaw = null;
        this.toRaw = null;
    }

    /**
     * Returns the parsed start date, if available.
     *
     * @return Parsed start date or {@code null} when only text was provided.
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Returns the parsed end date, if available.
     *
     * @return Parsed end date or {@code null} when only text was provided.
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * Returns the raw start text, if available.
     *
     * @return Raw start text or {@code null} when a structured date was
     * provided.
     */
    public String getFromRaw() {
        return fromRaw;
    }

    /**
     * Returns the raw end text, if available.
     *
     * @return Raw end text or {@code null} when a structured date was provided.
     */
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

    /**
     * Returns the event-specific string representation including its time span.
     *
     * @return {@link String} representation suitable for display.
     */
    @Override
    public String toString() {
        String fromDisplay = formatDisplay(fromDate, fromRaw);
        String toDisplay = formatDisplay(toDate, toRaw);
        return "[E]" + super.toString() + " (from: " + fromDisplay + " to: " + toDisplay + ")";
    }
}
