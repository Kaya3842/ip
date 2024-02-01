public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }
//    private static TodoTask parseTodoCommand(String input.txt) {
//        int spaceIndex = command.indexOf(' ');
//        String description = command.substring(spaceIndex + 1);
//        return new TodoTask(description);
//    }
    @Override
    public String getDisplayedString() {
        return "[T]" + getStatusIcon() + " " + getDescription();
    }

    @Override
    public String serializeTask() {
        return "[T] | " + getStatusIcon() + " | " + getDescription();
    }
}
