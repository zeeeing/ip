package buddy.exceptions;

/**
 * Signals that the user entered a command that Buddy cannot recognise or execute.
 */
public class InvalidCommandException extends BuddyException {
    public InvalidCommandException() {
        super("");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
