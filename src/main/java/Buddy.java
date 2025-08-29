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
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.trim().equalsIgnoreCase("bye")) {
                System.out.println("_________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_________________________________________________________");
                break;
            }

            System.out.println("_________________________________________________________");
            System.out.println("  " + input);
            System.out.println("_________________________________________________________");
        }

        scanner.close();
    }
}
