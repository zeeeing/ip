package buddy.exceptions;

/**
 * Signals that a required argument was not provided for a command.
 */
public class MissingArgumentException extends BuddyException {
    public MissingArgumentException(String message) {
        super(message);
    }
}
