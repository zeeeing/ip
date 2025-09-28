package buddy.parser;

import buddy.exceptions.BuddyException;
import buddy.exceptions.InvalidCommandException;
import buddy.exceptions.InvalidFormatException;
import buddy.exceptions.InvalidIndexException;
import buddy.exceptions.MissingArgumentException;
import buddy.tasks.Deadline;
import buddy.tasks.Event;
import buddy.tasks.Task;
import buddy.tasks.Todo;

public class Parser {
    public boolean isExit(String input) {
        return input != null && input.trim().equalsIgnoreCase("bye");
    }

    public boolean isList(String input) {
        return input != null && input.trim().equalsIgnoreCase("list");
    }

    public boolean isMarkCommand(String input) {
        if (input == null) {
            return false;
        }
        String trimmed = input.trim().toLowerCase();
        return trimmed.equals("mark") || trimmed.startsWith("mark ");
    }

    public boolean isUnmarkCommand(String input) {
        if (input == null) {
            return false;
        }
        String trimmed = input.trim().toLowerCase();
        return trimmed.equals("unmark") || trimmed.startsWith("unmark ");
    }

    public boolean isDeleteCommand(String input) {
        if (input == null) {
            return false;
        }
        String trimmed = input.trim().toLowerCase();
        return trimmed.equals("delete") || trimmed.startsWith("delete ");
    }

    public int parseTaskIndex(String input, String action, int max) throws BuddyException {
        String[] parts = input.trim().split("\\s+", 2);
        if (parts.length < 2) {
            throw new MissingArgumentException("Please provide a task number to " + action + ".");
        }

        int taskIdx;
        try {
            taskIdx = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException("That's not a valid number to " + action + ": '" + parts[1] + "'");
        }

        if (taskIdx <= 0 || taskIdx > max) {
            throw new InvalidIndexException("Invalid task number: " + taskIdx + "\nPlease pick between 1 and " + max);
        }

        // convert to 0-based for internal usage
        return taskIdx - 1;
    }

    public Task parseAddTask(String input) throws BuddyException {
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            throw new InvalidCommandException();
        }

        String[] tokens = trimmed.split("\\s+", 2);
        String command = tokens[0].toLowerCase();
        String rest = tokens.length > 1 ? tokens[1].trim() : "";

        switch (command) {
        case "todo":
            if (rest.isEmpty()) {
                throw new MissingArgumentException("The description of a todo cannot be empty.");
            }
            return new Todo(rest);

        case "deadline":
            if (rest.isEmpty()) {
                throw new InvalidFormatException("Invalid deadline format. Use: deadline <desc> /by <time>");
            }
            String[] parts = rest.split(" /by ", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new InvalidFormatException("Invalid deadline format. Use: deadline <desc> /by <time>");
            }
            return new Deadline(parts[0].trim(), parts[1].trim());

        case "event":
            if (rest.isEmpty()) {
                throw new InvalidFormatException("Invalid event format. Use: event <desc> /from <time> /to <time>");
            }
            String[] firstSplit = rest.split(" /from ", 2);
            if (firstSplit.length < 2 || firstSplit[0].trim().isEmpty()) {
                throw new InvalidFormatException("Invalid event format. Use: event <desc> /from <time> /to <time>");
            }
            String[] timeParts = firstSplit[1].split(" /to ", 2);
            if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                throw new InvalidFormatException("Invalid event format. Use: event <desc> /from <time> /to <time>");
            }
            return new Event(firstSplit[0].trim(), timeParts[0].trim(), timeParts[1].trim());
        default:
            throw new InvalidCommandException();
        }
    }
}
