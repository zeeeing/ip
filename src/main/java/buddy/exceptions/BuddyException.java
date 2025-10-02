package buddy.exceptions;

/**
 * Base class for application-specific exceptions thrown by Buddy.
 */
public class BuddyException extends Exception {
    public BuddyException(String message) {
        super(message);
    }
}
