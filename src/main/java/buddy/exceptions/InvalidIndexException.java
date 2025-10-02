package buddy.exceptions;

/**
 * Signals that a command referenced a task index outside the valid range.
 */
public class InvalidIndexException extends BuddyException {
    public InvalidIndexException(String message) {
        super(message);
    }
}
