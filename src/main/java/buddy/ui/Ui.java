package buddy.ui;

import java.util.List;

import buddy.tasks.Task;
import buddy.tasks.TaskList;

public class Ui {
    private static final String CHATBOT_NAME = "Buddy";
    private static final String BUDDY_LOGO = """
                                         ______     _    _    _____     _____    __   __
                                        |  ___ \\   | |  | |  |  __ \\   |  __ \\   \\ \\ / /
                                        | |___) |  | |  | |  | |  \\ \\  | |  \\ \\   \\ V /
                                        |  ___ \\   | |  | |  | |   | | | |   | |   | |
                                        | |___) |  | |__| |  | |__/ /  | |__/ /    | |
                                        |______/    \\____/   |_____/   |_____/     |_|
                                    """;

    public void printDivider() {
        System.out.println("    ____________________________________________________________");
    }

    public void welcomeMessage() {
        printDivider();
        System.out.println("\n    Hello from\n" + BUDDY_LOGO);
        printDivider();
        System.out.println("     Hello! I'm " + CHATBOT_NAME);
        System.out.println("     What can I do for you?");
        printDivider();
    }

    public void exitProgram() {
        printDivider();
        System.out.println("     Bye. Hope to see you again soon!");
        printDivider();
    }

    public void listAllTasks(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
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

    /**
     * Displays the list of tasks that matches the search query executed via the
     * find command. If no tasks match, an appropriate message is shown.
     * 
     * @param matches - The {@link List} of {@link Task}s that match the search
     * keyword. If no tasks match, this {@link List} is empty.
     */
    public void listMatchingTasks(List<Task> matches) {
        printDivider();
        if (matches == null || matches.isEmpty()) {
            System.out.println("     No matching tasks found.");
        } else {
            System.out.println("     Here are the matching tasks in your list:");
            int taskIdx = 1;
            for (Task task : matches) {
                System.out.println("     " + taskIdx + "." + task);
                taskIdx++;
            }
        }
        printDivider();
    }

    public void printHelp() {
        printDivider();
        System.out.println("     I'm sorry, but I don't know what that means :-(\n");
        System.out.println("     Please use commands that I can understand:\n");
        System.out.println("     1. list");
        System.out.println("     2. mark <task_number>");
        System.out.println("     3. todo <desc>");
        System.out.println("     4. deadline <desc> /by <time>");
        System.out.println("     5. event <desc> /from <time> /to <time>");
        System.out.println("     6. delete <task_number>");
        System.out.println("     7. find <keyword>");
        printDivider();
    }

    public void printTaskAddedSuccessMessage(Task newTask, int taskCount) {
        printDivider();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + newTask);
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        printDivider();
    }

    public void printTaskDeletedSuccessMessage(Task removedTask, int taskCount) {
        printDivider();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + removedTask);
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        printDivider();
    }

    public void printTaskMarkedSuccessMessage(Task task) {
        printDivider();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        printDivider();
    }

    public void printTaskUnmarkedSuccessMessage(Task task) {
        printDivider();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
        printDivider();
    }

    public void printError(String message) {
        printDivider();
        System.out.println("     " + message);
        printDivider();
    }
}
