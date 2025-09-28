package buddy;

import java.util.Scanner;

import buddy.exceptions.BuddyException;
import buddy.exceptions.InvalidCommandException;
import buddy.parser.Parser;
import buddy.storage.Storage;
import buddy.tasks.Task;
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
                if (parser.isExit(input)) {
                    ui.exitProgram();
                    break;
                } else if (parser.isList(input)) {
                    ui.listAllTasks(tasks);
                } else if (parser.isMarkCommand(input)) {
                    int idx = parser.parseTaskIndex(input, "mark", tasks.size());
                    Task t = tasks.get(idx);
                    t.mark();
                    ui.printTaskMarkedSuccessMessage(t);
                    storage.save(tasks.getTasks());
                } else if (parser.isUnmarkCommand(input)) {
                    int idx = parser.parseTaskIndex(input, "unmark", tasks.size());
                    Task t = tasks.get(idx);
                    t.unmark();
                    ui.printTaskUnmarkedSuccessMessage(t);
                    storage.save(tasks.getTasks());
                } else if (parser.isDeleteCommand(input)) {
                    int idx = parser.parseTaskIndex(input, "delete", tasks.size());
                    Task removedTask = tasks.delete(idx);
                    ui.printTaskDeletedSuccessMessage(removedTask, tasks.size());
                    storage.save(tasks.getTasks());
                } else {
                    Task newTask = parser.parseAddTask(input);
                    tasks.add(newTask);
                    ui.printTaskAddedSuccessMessage(newTask, tasks.size());
                    storage.save(tasks.getTasks());
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
