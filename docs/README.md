# Buddy User Guide

Buddy is a lightweight command-line companion that helps you track todos, deadlines, and events. It stores everything in `data/tasks.txt`, so your task list is ready each time you start the app.

## Quick Start

1. Ensure you have Java 17 or above installed.
2. Download the latest `.jar` file from [here](https://github.com/zeeeing/ip/releases/latest).
3. Copy the file to the folder you want to use as the home folder for your Buddy.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Buddy.jar` command to run the application.
5. Type commands into the prompt that appears. Buddy creates its data file automatically if it is missing.
6. Enter `bye` at any time to save your list and quit.

## Working With Commands

- Commands are single-line instructions, e.g. `todo read book`.
- Task numbers shown in lists start at 1.
- Dates typed in ISO format (`YYYY-MM-DD`) are recognised and displayed in a friendly form (e.g. `2024-04-09` becomes `Apr 09 2024`). Other formats are stored exactly as you type them.
- Buddy prints a divider before and after every response so you can clearly see what changed.

## Command Reference

### Viewing your tasks - `list`

- **Format:** `list`
- **Result:** Shows all saved tasks in the order they were added.

```
list
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
     2.[D][ ] return book (by: Apr 09 2024)
    ____________________________________________________________
```

### Adding a todo - `todo`

- **Format:** `todo <description>`
- **Example:** `todo read book`
- **Result:** Adds a simple task with no date attached.

```
todo read book
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] read book
     Now you have 1 tasks in the list.
    ____________________________________________________________
```

### Adding a deadline - `deadline`

- **Format:** `deadline <description> /by <date or time>`
- **Example:** `deadline return book /by 2024-04-09`
- **Result:** Adds a task that is due on the supplied date/time.

```
deadline return book /by 2024-04-09
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] return book (by: Apr 09 2024)
     Now you have 2 tasks in the list.
    ____________________________________________________________
```

### Adding an event - `event`

- **Format:** `event <description> /from <start> /to <end>`
- **Example:** `event cs2113 meeting /from 2024-04-10 /to 2024-04-10`
- **Result:** Adds an event that spans the supplied start and end values.

```
event cs2113 meeting /from 2024-04-10 /to 2024-04-10
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] cs2113 meeting (from: Apr 10 2024 to: Apr 10 2024)
     Now you have 3 tasks in the list.
    ____________________________________________________________
```

### Marking a task as done - `mark`

- **Format:** `mark <task_number>`
- **Example:** `mark 1`
- **Result:** Marks the chosen task as completed.

```
mark 1
    ____________________________________________________________
     Nice! I've marked this task as done:
       [T][X] read book
    ____________________________________________________________
```

### Reopening a task - `unmark`

- **Format:** `unmark <task_number>`
- **Example:** `unmark 1`
- **Result:** Marks the chosen task as not done.

```
unmark 1
    ____________________________________________________________
     OK, I've marked this task as not done yet:
       [T][ ] read book
    ____________________________________________________________
```

### Deleting a task - `delete`

- **Format:** `delete <task_number>`
- **Example:** `delete 2`
- **Result:** Removes the selected task from the list.

```
delete 2
    ____________________________________________________________
     Noted. I've removed this task:
       [D][ ] return book (by: Apr 09 2024)
     Now you have 2 tasks in the list.
    ____________________________________________________________
```

### Searching for tasks - `find`

- **Format:** `find <keyword>`
- **Example:** `find book`
- **Result:** Lists tasks whose descriptions contain the keyword (case-insensitive).

```
find book
    ____________________________________________________________
     Here are the matching tasks in your list:
     1.[T][ ] read book
    ____________________________________________________________
```

### Leaving Buddy - `bye`

- **Format:** `bye`
- **Result:** Saves your tasks and exits the program.

```
bye
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Troubleshooting

- **Unknown command message:** Buddy shows the list of valid commands if it does not recognise what you typed. Re-enter the command in the documented format.
- **Invalid task number:** Ensure you are using the 1-based index shown by `list` or `find`.
- **Storage file issues:** Buddy creates `data/tasks.txt` automatically. Delete the file if it becomes corrupted and Buddy will start with a clean slate.

## Command Summary

| Command    | Format                                        | Purpose                                                |
| ---------- | --------------------------------------------- | ------------------------------------------------------ |
| `list`     | `list`                                        | Display every task currently saved.                    |
| `todo`     | `todo <description>`                          | Add a new todo without any dates.                      |
| `deadline` | `deadline <description> /by <date or time>`   | Add a task that is due by a specific time.             |
| `event`    | `event <description> /from <start> /to <end>` | Add an event that spans a start and end time.          |
| `mark`     | `mark <task_number>`                          | Mark a task as completed.                              |
| `unmark`   | `unmark <task_number>`                        | Mark a task as not completed.                          |
| `delete`   | `delete <task_number>`                        | Remove a task from the list.                           |
| `find`     | `find <keyword>`                              | Search for tasks whose descriptions contain a keyword. |
| `bye`      | `bye`                                         | Save tasks and exit Buddy.                             |

Happy tasking with Buddy!
