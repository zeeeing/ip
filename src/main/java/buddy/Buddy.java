package buddy;

import java.util.ArrayList;
import java.util.Scanner;

import buddy.exceptions.BuddyException;
import buddy.exceptions.InvalidCommandException;
import buddy.exceptions.InvalidFormatException;
import buddy.exceptions.InvalidIndexException;
import buddy.exceptions.MissingArgumentException;
import buddy.tasks.Deadline;
import buddy.tasks.Event;
import buddy.tasks.Task;
import buddy.tasks.Todo;

public class Buddy {
    private static final String CHATBOT_NAME = "Buddy";
    private static final String BUDDY_LOGO = """
                 ______     _    _    _____     _____    __   __
                |  ___ \\   | |  | |  |  __ \\   |  __ \\   \\ \\ / /
                | |___) |  | |  | |  | |  \\ \\  | |  \\ \\   \\ V /
                |  ___ \\   | |  | |  | |   | | | |   | |   | |
                | |___) |  | |__| |  | |__/ /  | |__/ /    | |
                |______/    \\____/   |_____/   |_____/     |_|
            """;

    public static void printDivider() {
        System.out.println("    ____________________________________________________________");
    }

    public static void welcomeMessage() {
        printDivider();
        System.out.println("\n    Hello from\n" + BUDDY_LOGO);
        printDivider();
        System.out.println("     Hello! I'm " + CHATBOT_NAME);
        System.out.println("     What can I do for you?");
        printDivider();
    }

    public static void exitProgram() {
        printDivider();
        System.out.println("     Bye. Hope to see you again soon!");
        printDivider();
    }

    public static void listAllTasks(ArrayList<Task> tasks) {
        printDivider();
        if (tasks.isEmpty()) {
            System.out.println("     You currently do not have any recorded tasks.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            int taskIndex = 1;
            for (Task task : tasks) {
                System.out.println("     " + taskIndex + "." + task);
                taskIndex++;
            }
        }
        printDivider();
    }

    public static void printHelp() {
        printDivider();
        System.out.println("     I'm sorry, but I don't know what that means :-(\n");
        System.out.println("     Please use commands that I can understand:\n");
        System.out.println("     1. list");
        System.out.println("     2. mark <task_number>");
        System.out.println("     3. todo <desc>");
        System.out.println("     4. deadline <desc> /by <time>");
        System.out.println("     5. event <desc> /from <time> /to <time>");
        printDivider();
    }

    public static void taskAddedSuccessMessage(Task newTask, int taskCounter) {
        printDivider();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + newTask);
        System.out.println("     Now you have " + taskCounter + " tasks in the list.");
        printDivider();
    }

    public static Task addTask(String input) throws BuddyException {
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

    public static void main(String[] args) {
        welcomeMessage();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            try {
                // exit program
                if (input.trim().equalsIgnoreCase("bye")) {
                    exitProgram();
                    break;
                }

                // list tasks
                else if (input.trim().equalsIgnoreCase("list")) {
                    listAllTasks(tasks);
                }

                // mark task as completed
                else if (input.trim().equalsIgnoreCase("mark") || input.trim().toLowerCase().startsWith("mark ")) {
                    String[] parts = input.trim().split("\\s+", 2);
                    if (parts.length < 2) {
                        throw new MissingArgumentException("Please provide a task number to mark.");
                    }

                    int idx;
                    try {
                        idx = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        throw new InvalidIndexException("That's not a valid number to mark: '" + parts[1] + "'");
                    }

                    if (idx <= 0 || idx > tasks.size()) {
                        throw new InvalidIndexException(
                                "Invalid task number: " + idx + "\nPlease pick between 1 and " + tasks.size());
                    }

                    Task t = tasks.get(idx - 1);
                    t.mark();

                    printDivider();
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + t);
                    printDivider();
                }

                // unmark task
                else if (input.trim().equalsIgnoreCase("unmark") || input.trim().toLowerCase().startsWith("unmark ")) {
                    String[] parts = input.trim().split("\\s+", 2);
                    if (parts.length < 2) {
                        throw new MissingArgumentException("Please provide a task number to unmark.");
                    }

                    int idx;
                    try {
                        idx = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        throw new InvalidIndexException("That's not a valid number to unmark: '" + parts[1] + "'");
                    }

                    if (idx <= 0 || idx > tasks.size()) {
                        throw new InvalidIndexException(
                                "Invalid task number: " + idx + "\nPlease pick between 1 and " + tasks.size());
                    }

                    Task t = tasks.get(idx - 1);
                    t.unmark();

                    printDivider();
                    System.out.println("     OK, I've marked this task as not done yet:");
                    System.out.println("       " + t);
                    printDivider();
                }

                // add task
                else {
                    Task newTask = addTask(input);
                    tasks.add(newTask);
                    taskAddedSuccessMessage(newTask, tasks.size());
                }
            } catch (InvalidCommandException e) {
                printHelp();
            } catch (BuddyException e) {
                printDivider();
                System.out.println("     " + e.getMessage());
                printDivider();
            } catch (Exception e) {
                printDivider();
                System.out.println("     An unexpected error occurred.");
                printDivider();
            }
        }

        scanner.close();
    }
}
