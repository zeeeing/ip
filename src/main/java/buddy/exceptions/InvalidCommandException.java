package buddy.exceptions;
public class InvalidCommandException extends BuddyException {
    public InvalidCommandException() {
        super("");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
