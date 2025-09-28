package buddy;

import java.util.Scanner;

import buddy.commands.Command;
import buddy.exceptions.BuddyException;
import buddy.exceptions.InvalidCommandException;
import buddy.parser.Parser;
import buddy.storage.Storage;
import buddy.tasks.TaskList;
import buddy.ui.Ui;

public class Buddy {
    public static void main(String[] args) {
        Storage storage = new Storage("data/tasks.txt");
        TaskList tasks = new TaskList(storage.load());
        Ui ui = new Ui();
        Parser parser = new Parser();

        ui.welcomeMessage();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            try {
                Command command = parser.parse(input);
                command.execute(tasks, ui, storage);
                if (command.isExit()) {
                    break;
                }
            } catch (InvalidCommandException e) {
                ui.printHelp();
            } catch (BuddyException e) {
                ui.printError(e.getMessage());
            } catch (Exception e) {
                ui.printError("An unexpected error occurred.");
            }
        }

        scanner.close();
    }
}
