package buddy.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import buddy.commands.AddCommand;
import buddy.commands.Command;
import buddy.commands.DeleteCommand;
import buddy.commands.ExitCommand;
import buddy.commands.FindCommand;
import buddy.commands.ListCommand;
import buddy.commands.MarkCommand;
import buddy.commands.UnmarkCommand;
import buddy.exceptions.BuddyException;
import buddy.exceptions.InvalidCommandException;
import buddy.exceptions.InvalidFormatException;
import buddy.exceptions.InvalidIndexException;
import buddy.exceptions.MissingArgumentException;
import buddy.tasks.Deadline;
import buddy.tasks.Event;
import buddy.tasks.Todo;

public class Parser {
    /**
     * Parses a single line of user input into a {@link Command} instance. Input
     * is expected to be a command keyword followed by zero or more arguments,
     * separated by whitespace. Structured commands may have additional syntax
     * requirements.
     *
     * @param input - Raw user input line, containing a command keyword and
     * arguments, if any.
     * @return The concrete {@link Command} representing the requested action.
     * @throws InvalidCommandException If the command keyword is missing/unknown
     * or extra arguments are provided.
     * @throws MissingArgumentException If a required argument is missing.
     * @throws InvalidFormatException If a structured command has an invalid
     * format.
     * @throws InvalidIndexException If an index cannot be parsed as a positive
     * integer.
     */
    public Command parse(String input) throws BuddyException {
        // empty input check
        String trimmed = input == null ? "" : input.trim();
        if (trimmed.isEmpty()) {
            throw new InvalidCommandException();
        }

        // split command and arguments
        String[] tokens = trimmed.split("\\s+", 2);
        String command = tokens[0].toLowerCase();
        String arguments = tokens.length > 1 ? tokens[1].trim() : "";

        switch (command) {
        case "bye":
            if (!arguments.isEmpty()) {
                throw new InvalidCommandException();
            }
            return new ExitCommand();
        case "list":
            if (!arguments.isEmpty()) {
                throw new InvalidCommandException();
            }
            return new ListCommand();
        case "mark":
            if (arguments.isEmpty()) {
                throw new MissingArgumentException("Please provide a task number to mark.");
            }
            return new MarkCommand(parseIndex0Based(arguments));
        case "unmark":
            if (arguments.isEmpty()) {
                throw new MissingArgumentException("Please provide a task number to unmark.");
            }
            return new UnmarkCommand(parseIndex0Based(arguments));
        case "delete":
            if (arguments.isEmpty()) {
                throw new MissingArgumentException("Please provide a task number to delete.");
            }
            return new DeleteCommand(parseIndex0Based(arguments));
        case "find":
            if (arguments.isEmpty()) {
                throw new MissingArgumentException("Please provide a keyword to search for.");
            }
            return new FindCommand(arguments);
        case "todo":
            if (arguments.isEmpty()) {
                throw new MissingArgumentException("The description of a todo cannot be empty.");
            }
            return new AddCommand(new Todo(arguments));
        case "deadline":
            if (arguments.isEmpty()) {
                throw new InvalidFormatException("Invalid deadline format. Use: deadline <desc> /by <time>");
            }

            String[] parts = arguments.split(" /by ", 2);
            String deadlineDesc = parts[0].trim();
            String by = parts.length > 1 ? parts[1].trim() : "";
            if (parts.length < 2 || deadlineDesc.isEmpty() || by.isEmpty()) {
                throw new InvalidFormatException("Invalid deadline format. Use: deadline <desc> /by <time>");
            }

            Deadline deadlineTask;
            try {
                LocalDate date = LocalDate.parse(by, DateTimeFormatter.ISO_LOCAL_DATE);
                deadlineTask = new Deadline(deadlineDesc, date);
            } catch (DateTimeParseException e) {
                // fallback to storing raw value
                deadlineTask = new Deadline(deadlineDesc, by);
            }

            return new AddCommand(deadlineTask);
        case "event":
            if (arguments.isEmpty()) {
                throw new InvalidFormatException("Invalid event format. Use: event <desc> /from <time> /to <time>");
            }

            String[] firstSplit = arguments.split(" /from ", 2);
            String eventDesc = firstSplit[0].trim();
            if (firstSplit.length < 2 || eventDesc.isEmpty()) {
                throw new InvalidFormatException("Invalid event format. Use: event <desc> /from <time> /to <time>");
            }

            String[] timeParts = firstSplit[1].split(" /to ", 2);
            String from = timeParts[0].trim();
            String to = timeParts.length > 1 ? timeParts[1].trim() : "";
            if (timeParts.length < 2 || from.isEmpty() || to.isEmpty()) {
                throw new InvalidFormatException("Invalid event format. Use: event <desc> /from <time> /to <time>");
            }

            try {
                LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate toDate = LocalDate.parse(to, DateTimeFormatter.ISO_LOCAL_DATE);
                return new AddCommand(new Event(eventDesc, fromDate, toDate));
            } catch (DateTimeParseException e) {
                // fallback to storing raw values
                return new AddCommand(new Event(eventDesc, from, to));
            }
        default:
            throw new InvalidCommandException();
        }
    }

    private int parseIndex0Based(String s) throws InvalidIndexException {
        try {
            return Integer.parseInt(s) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException("That's not a valid number: '" + s + "'");
        }
    }
}
