import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.println("     Here are the tasks in your list:");
        int x = 1;
        for (Task task : tasks) {
            System.out.println("     " + x + "." + task);
            x++;
        }
        printDivider();
    }

    public static void unknownUserInputMessage() {
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

    public static Task addTask(String input) {
        if (input.trim().toLowerCase().startsWith("todo ")) {
            String description = input.trim().substring(5).trim();
            if (description.isEmpty()) {
                printDivider();
                System.out.println("     The description of a todo cannot be empty.");
                printDivider();
                return null;
            }
            return new Todo(description);
        } else if (input.trim().toLowerCase().startsWith("deadline ")) {
            String[] parts = input.trim().substring(9).split(" /by ", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                printDivider();
                System.out.println("     Invalid deadline format. Use: deadline <desc> /by <time>");
                printDivider();
                return null;
            }
            return new Deadline(parts[0].trim(), parts[1].trim());
        } else if (input.trim().toLowerCase().startsWith("event ")) {
            // split the string into 2 parts at /from first
            String[] parts = input.trim().substring(6).split(" /from ", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                printDivider();
                System.out.println("     Invalid event format. Use: event <desc> /from <time> /to <time>");
                printDivider();
                return null;
            }
            // further split the 2nd part of the initial string into 2 parts at /to
            String[] timeParts = parts[1].split(" /to ", 2);
            if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                printDivider();
                System.out.println("     Invalid event format. Use: event <desc> /from <time> /to <time>");
                printDivider();
                return null;
            }
            return new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
        } else {
            unknownUserInputMessage();
            return null;
        }
    }

    public static void main(String[] args) {
        welcomeMessage();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

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
            else if (input.trim().toLowerCase().startsWith("mark ")) {
                // split into at most 2 parts: "mark" and the number
                String[] parts = input.trim().split("\\s+", 2);

                // check if a number is provided
                if (parts.length < 2) {
                    printDivider();
                    System.out.println("     Please provide a task number to mark.");
                    printDivider();
                    continue;
                }

                try {
                    int idx = Integer.parseInt(parts[1]);

                    // check if index is valid
                    if (idx <= 0 || idx > tasks.size()) {
                        printDivider();
                        System.out.println("     Invalid task number: " + idx);
                        System.out.println("     Please pick between 1" + " and " + tasks.size());
                        printDivider();
                        continue;
                    }

                    Task t = tasks.get(idx - 1);
                    t.mark();

                    printDivider();
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + t);
                    printDivider();
                } catch (NumberFormatException e) {
                    printDivider();
                    System.out.println("     That's not a valid number to mark: '" + parts[1] + "'");
                    printDivider();
                }
            }

            // unmark task
            else if (input.trim().toLowerCase().startsWith("unmark ")) {
                // split into at most 2 parts: "mark" and the number
                String[] parts = input.trim().split("\\s+", 2);

                // check if a number is provided
                if (parts.length < 2) {
                    printDivider();
                    System.out.println("     Please provide a task number to unmark.");
                    printDivider();
                    continue;
                }

                try {
                    int idx = Integer.parseInt(parts[1]);

                    // check if index is valid
                    if (idx <= 0 || idx > tasks.size()) {
                        printDivider();
                        System.out.println("     Invalid task number: " + idx);
                        System.out.println("     Please pick between 1" + " and " + tasks.size());
                        printDivider();
                        continue;
                    }

                    Task t = tasks.get(idx - 1);
                    t.unmark();

                    printDivider();
                    System.out.println("     OK, I've marked this task as not done yet:");
                    System.out.println("       " + t);
                    printDivider();
                } catch (NumberFormatException e) {
                    printDivider();
                    System.out.println("     That's not a valid number to unmark: '" + parts[1] + "'");
                    printDivider();
                }
            }

            // add task
            else {
                try {
                    Task newTask = addTask(input);
                    if (newTask != null) {
                        tasks.add(newTask);
                        taskAddedSuccessMessage(newTask, tasks.size());
                    }
                } catch (Exception e) {
                    printDivider();
                    System.out.println("     An error occurred: " + e.getMessage());
                    printDivider();
                }
            }
        }

        scanner.close();
    }
}
