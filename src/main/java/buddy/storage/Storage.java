package buddy.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import buddy.tasks.Deadline;
import buddy.tasks.Event;
import buddy.tasks.Task;
import buddy.tasks.Todo;

public class Storage {
    private static final String DATA_DIRECTORY = "data";
    private static final String FILE_NAME = "tasks.txt";

    public List<Task> load() {
        File file = ensureFileReady();

        List<Task> tasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Task task = parseTask(scanner.nextLine());
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("     Unable to read saved tasks. Starting with an empty list.");
        }

        return tasks;
    }

    public void save(List<Task> tasks) {
        File file = ensureFileReady();

        try (FileWriter writer = new FileWriter(file, false)) {
            for (Task task : tasks) {
                String formatted = formatTask(task);
                if (formatted != null) {
                    writer.write(formatted);
                    writer.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("     Unable to save tasks to disk.");
        }
    }

    private File ensureFileReady() {
        File directory = new File(DATA_DIRECTORY);
        if (!directory.exists() && !directory.mkdirs()) {
            System.out.println("     Unable to create data directory.");
        }

        File file = new File(directory, FILE_NAME);
        try {
            if (!file.exists() && !file.createNewFile()) {
                System.out.println("     Unable to create data file.");
            }
        } catch (IOException e) {
            System.out.println("     Unable to create data file.");
        }

        return file;
    }

    private Task parseTask(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split("\\s*\\|\\s*", -1);
        if (parts.length < 3) {
            System.out.println("     Skipping malformed stored task: " + line);
            return null;
        }

        String type = parts[0];
        String status = parts[1];
        String description = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length < 4) {
                System.out.println("     Skipping malformed stored deadline task: " + line);
                return null;
            }
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            if (parts.length < 5) {
                System.out.println("     Skipping malformed stored event task: " + line);
                return null;
            }
            task = new Event(description, parts[3], parts[4]);
            break;
        default:
            System.out.println("     Skipping unknown task type in storage: " + type);
            return null;
        }

        if ("1".equals(status)) {
            task.mark();
        }

        return task;
    }

    private String formatTask(Task task) {
        String status = task.isCompleted() ? "1" : "0";

        if (task instanceof Todo) {
            return String.format("T | %s | %s", status, task.getDescription());
        }

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.format("D | %s | %s | %s", status, task.getDescription(), deadline.getBy());
        }

        if (task instanceof Event) {
            Event event = (Event) task;
            return String.format("E | %s | %s | %s | %s", status, task.getDescription(), event.getFrom(),
                                            event.getTo());
        }

        return null;
    }
}
