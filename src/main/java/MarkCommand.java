public class MarkCommand extends Command {
    private int index;
    
    public MarkCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException {
        Task task = tasks.getTask(index);
        task.setDone();
        storage.save(tasks);
        ui.printMessage("Nice! I've marked this task as done:\n  " + task.toString());
    }
}
