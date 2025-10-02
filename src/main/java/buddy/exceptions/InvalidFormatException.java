package buddy.exceptions;

/**
 * Signals that a command was supplied with arguments in an unexpected format.
 */
public class InvalidFormatException extends BuddyException {
    public InvalidFormatException(String message) {
        super(message);
    }
}
