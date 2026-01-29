public class AddTodoCommand extends Command {
    private String description;
    
    public AddTodoCommand(String description) {
        this.description = description;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException {
        if (description == null || description.trim().isEmpty()) {
            throw new ZaneException("The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(description.trim());
        tasks.addTask(todo);
        storage.save(tasks);
        ui.printAddedTask(todo, tasks.size());
    }
}
