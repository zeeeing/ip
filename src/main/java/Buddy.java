import java.util.ArrayList;
import java.util.Scanner;

public class Buddy {
    public static void main(String[] args) {
        String logo = """
                     ______     _    _    _____     _____    __   __
                    |  ___ \\   | |  | |  |  __ \\   |  __ \\   \\ \\ / /
                    | |___) |  | |  | |  | |  \\ \\  | |  \\ \\   \\ V /
                    |  ___ \\   | |  | |  | |   | | | |   | |   | |
                    | |___) |  | |__| |  | |__/ /  | |__/ /    | |
                    |______/    \\____/   |_____/   |_____/     |_|
                """;
        String chatbotName = "Buddy";

        System.out.println("_________________________________________________________");
        System.out.println("\n    Hello from\n" + logo);
        System.out.println("_________________________________________________________");
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");
        System.out.println("_________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

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
                for (String task : tasks) {
                    System.out.println("  " + x + ". " + task);
                    x++;
                }
                System.out.println("  _______________________________________________________");
            }

            // add task to list
            else {
                tasks.add(input);
                System.out.println("  _______________________________________________________");
                System.out.println("  added: " + input);
                System.out.println("  _______________________________________________________");
            }
        }

        scanner.close();
    }
}
