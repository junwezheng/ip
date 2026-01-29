public class DeleteCommand extends Command {
    private int index;
    
    public DeleteCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException {
        Task task = tasks.getTask(index);
        tasks.removeTask(index);
        storage.save(tasks);
        ui.printRemoveTask(task, tasks.size());
    }
}
