package liv.ui;

import java.util.ArrayList;
import java.util.Scanner;

import liv.container.TaskList;
import liv.task.Deadline;
import liv.task.Event;
import liv.task.Task;
import liv.task.TodoTask;

/**
 * Handles the interaction between users and the chatbot.
 */
public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Displays the long bar between messages.
     */
    public void displayBar() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the greeting message.
     */
    public static String getGreetingMessage() {
        String greetingMessage = String.join("\n",
                "Liv, under your instructions!", "What is your command?");
        //displayMessage(greetingMessage);
        return greetingMessage;
    }

    /**
     * Reads the input command from the users.
     * @return The line that user types.
     */
    public String readCommand() {
        return SCANNER.nextLine();
    }

    /**
     * Prints the goodbye message.
     */
    public static String getByeMessage() {
        String byeMessage = String.join("\n", "Thank you for your hard work.",
                "Please take a good rest!");
        displayMessage(byeMessage);
        return byeMessage;
    }

    public static String getClearMessage() {
        String clearMessage = "Got it! I have cleared your mission list.";
        displayMessage(clearMessage);
        return clearMessage;
    }

    /**
     * Prints the list of tasks to the user.
     */
    public static String getListMessage() {
        String listMessage = "Here are the missions for you:\n";

        for (int i = 0; i < TaskList.getListSize(); i++) {
            Task task = TaskList.getTask(i);
            int displayedIndex = i + 1;
            listMessage += displayedIndex + ". " + task.getDisplayedString() + "\n";
        }

        listMessage += "Total: " + TaskList.getListSize() + " mission(s)";
        displayMessage(listMessage);
        return listMessage;
    }

    /**
     * Prints the task that was marked by the command from the user.
     * @param indices The list of indices of the tasks that was marked.
     * @param markedTasks The list of tasks that was marked.
     */
    public static String getMarkMessage(ArrayList<Integer> indices, ArrayList<Task> markedTasks) {
        assert indices.size() == markedTasks.size();
        String markMessage = "Mission(s) completed:\n";

        for (int i = 0; i < markedTasks.size(); i++) {
            int displayedIndex = indices.get(i) + 1;
            String taskDescription = markedTasks.get(i).getDisplayedString();

            markMessage += displayedIndex + ". " + taskDescription + "\n";
        }

        markMessage += "Total: " + markedTasks.size() + " mission(s)";
        displayMessage(markMessage);
        return markMessage;
    }

    /**
     * Prints the task that was unmarked by the command from the user.
     * @param indices The list of indices of the tasks that was unmarked.
     * @param unmarkedTasks The list of tasks that was unmarked.
     */
    public static String getUnmarkMessage(ArrayList<Integer> indices, ArrayList<Task> unmarkedTasks) {
        assert indices.size() == unmarkedTasks.size();
        String unmarkMessage = "Mission(s) pending:\n";

        for (int i = 0; i < unmarkedTasks.size(); i++) {
            int displayedIndex = indices.get(i) + 1;
            String taskDescription = unmarkedTasks.get(i).getDisplayedString();

            unmarkMessage += displayedIndex + ". " + taskDescription + "\n";
        }

        unmarkMessage += "Total: " + unmarkedTasks.size() + " mission(s)";
        displayMessage(unmarkMessage);
        return unmarkMessage;
    }

    /**
     * Prints the task that was deleted from the list by the user.
     * @param indices The list of indices of the tasks that was deleted.
     * @param deletedTasks The list of tasks removed from the list.
     */
    public static String getDeleteMessage(ArrayList<Integer> indices, ArrayList<Task> deletedTasks) {
        assert indices.size() == deletedTasks.size();
        String deleteMessage = "Mission(s) removed from list:\n";

        for (int i = 0; i < deletedTasks.size(); i++) {
            int displayedIndex = indices.get(i) + 1;
            String taskDescription = deletedTasks.get(i).getDisplayedString();

            deleteMessage += displayedIndex + ". " + taskDescription + "\n";
        }

        deleteMessage += "Total: " + deletedTasks.size() + " mission(s)";
        displayMessage(deleteMessage);
        return deleteMessage;
    }

    /**
     * Prints the task that was added by the user in the predefined format.
     * @param todo The task that user added to the list.
     */
    public static String getTodoMessage(TodoTask todo) {
        String todoMessage = String.join("\n", "Task added:", todo.getDisplayedString(),
                "You have " + TaskList.getListSize() + " mission(s) on the list");
        displayMessage(todoMessage);
        return todoMessage;
    }

    /**
     * Prints the deadline that was added by the user in the predefined format.
     * @param deadline The deadline that user added to the list.
     */
    public static String getDeadlineMessage(Deadline deadline) {
        String deadlineMessage = String.join("\n", "Deadline added:", deadline.getDisplayedString(),
                "You have " + TaskList.getListSize() + " mission(s) on the list");
        displayMessage(deadlineMessage);
        return deadlineMessage;
    }

    /**
     * Prints the deadline that was added by the user in the predefined format.
     * @param event The event that user added to the list.
     */
    public static String getEventMessage(Event event) {
        String eventMessage = String.join("\n", "Event added:", event.getDisplayedString(),
                "You have " + TaskList.getListSize() + " mission(s) on the list");
        displayMessage(eventMessage);
        return eventMessage;
    }

    /**
     * Displays the texts and results of the "find" command.
     * @param matchingTasks The list of tasks that is the result of the "find" command.
     */
    public static String getFindMessage(ArrayList<String> matchingTasks) {
        String findMessage = "Here are the mission(s) you requested me to find:\n";
        if (matchingTasks.size() == 0) {
            return "No mission found!";
        } else {
            for (int i = 0; i < matchingTasks.size(); i++) {
                findMessage += matchingTasks.get(i) + "\n";
            }
            findMessage += "Total: " + matchingTasks.size() + " mission(s)";
        }
        displayMessage(findMessage);
        return findMessage;
    }
    /**
     * Prints the error message that was thrown by the Exception class.
     * @param errorMessage The exception message thrown.
     */
    public static void displayErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }
}
