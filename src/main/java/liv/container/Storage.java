package liv.container;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import liv.task.Task;
import liv.task.TodoTask;
import liv.task.Deadline;
import liv.task.Event;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void handleLineFromDataFile(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("[X]");
        String description = parts[2];
        Task task;
        if (taskType.equals("[T]")) {
            task = new TodoTask(description);
        } else if (taskType.equals("[D]")) {
            String time = parts[3];
            task = new Deadline(description, LocalDateTime.parse(time));
        } else if (taskType.equals("[E]")) {
            String time = parts[3];
            int splitterIndex = time.indexOf(' ');
            String from = time.substring(0, splitterIndex);
            String to = time.substring(splitterIndex + 1);
            task = new Event(description, LocalDateTime.parse(from), LocalDateTime.parse(to));
        } else {
            throw new RuntimeException("Unknown format: " + line);
        }
        TaskList.addTask(task);
        if (isDone)
            task.changeStatus();
    }
    public void loadDataFile() {
        // Data format: [<task type>] | [<isDone>] | <description> | <time>
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines()
                    .forEach(line -> handleLineFromDataFile(line));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void saveTaskToFile() {
        // Data format: [<task type>] | [<isDone>] | <description> | <time>
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < TaskList.getListSize(); i++) {
                writer.write(TaskList.getTask(i).serializeTask() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}