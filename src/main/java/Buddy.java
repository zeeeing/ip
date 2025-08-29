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

    public static void main(String[] args) {
        System.out.println("_________________________________________________________");
        System.out.println("\n    Hello from\n" + BUDDY_LOGO);
        System.out.println("_________________________________________________________");
        System.out.println("Hello! I'm " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println("_________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            // exit program
            if (input.trim().equalsIgnoreCase("bye")) {
                System.out.println("  _______________________________________________________");
                System.out.println("  Bye. Hope to see you again soon!");
                System.out.println("  _______________________________________________________");
                break;
            }

            // list tasks
            else if (input.trim().equalsIgnoreCase("list")) {
                System.out.println("  _______________________________________________________");
                System.out.println("  Here are the tasks in your list:");
                int x = 1;
                for (Task task : tasks) {
                    System.out.println("  " + x + ". " + task);
                    x++;
                }
                System.out.println("  _______________________________________________________");
            }

            // mark task as completed
            else if (input.trim().toLowerCase().startsWith("mark ")) {
                // split into at most 2 parts: "mark" and the number
                String[] parts = input.trim().split("\\s+", 2);

                // check if a number is provided
                if (parts.length < 2) {
                    System.out.println("  _______________________________________________________");
                    System.out.println("  Please provide a task number to mark.");
                    System.out.println("  _______________________________________________________");
                    continue;
                }

                try {
                    int idx = Integer.parseInt(parts[1]);

                    // check if index is valid
                    if (idx <= 0 || idx > tasks.size()) {
                        System.out.println("  _______________________________________________________");
                        System.out.println("  Invalid task number: " + idx);
                        System.out.println("  _______________________________________________________");
                        continue;
                    }

                    Task t = tasks.get(idx - 1);
                    t.mark();

                    System.out.println("  _______________________________________________________");
                    System.out.println("  Nice! I've marked this task as done:");
                    System.out.println("    " + t);
                    System.out.println("  _______________________________________________________");
                } catch (NumberFormatException e) {
                    System.out.println("  _______________________________________________________");
                    System.out.println("  That's not a valid number to mark: '" + parts[1] + "'");
                    System.out.println("  _______________________________________________________");
                }
            }

            // unmark task
            else if (input.trim().toLowerCase().startsWith("unmark ")) {
                // split into at most 2 parts: "mark" and the number
                String[] parts = input.trim().split("\\s+", 2);

                // check if a number is provided
                if (parts.length < 2) {
                    System.out.println("  _______________________________________________________");
                    System.out.println("  Please provide a task number to unmark.");
                    System.out.println("  _______________________________________________________");
                    continue;
                }

                try {
                    int idx = Integer.parseInt(parts[1]);

                    // check if index is valid
                    if (idx <= 0 || idx > tasks.size()) {
                        System.out.println("  _______________________________________________________");
                        System.out.println("  Invalid task number: " + idx);
                        System.out.println("  _______________________________________________________");
                        continue;
                    }

                    Task t = tasks.get(idx - 1);
                    t.unmark();

                    System.out.println("  _______________________________________________________");
                    System.out.println("  OK, I've marked this task as not done yet:");
                    System.out.println("    " + t);
                    System.out.println("  _______________________________________________________");
                } catch (NumberFormatException e) {
                    System.out.println("  _______________________________________________________");
                    System.out.println("  That's not a valid number to unmark: '" + parts[1] + "'");
                    System.out.println("  _______________________________________________________");
                }
            }

            // add task to list
            else {
                // trim description
                String desc = input.trim();

                // instantiate new Task object
                Task newTask = new Task(desc);

                // add task to list
                tasks.add(newTask);

                System.out.println("  _______________________________________________________");
                System.out.println("  added: " + desc);
                System.out.println("  _______________________________________________________");
            }
        }

        scanner.close();
    }
}
