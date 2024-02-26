package liv.processor;

import liv.container.Storage;
import liv.container.TaskList;
import liv.exception.LivException;
import liv.ui.Ui;

public class ClearCommand extends Command {
    /**
     * Executes this command.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The Ui to gives interaction with users.
     * @param storage The storage where the data is stored.
     * @throws LivException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LivException {
        tasks.clearTasks();
        String message = Ui.getClearMessage();
        storage.saveTaskToFile();
        return message;
    }
}
