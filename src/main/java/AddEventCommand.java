public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;
    
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException {
        if (description == null || description.trim().isEmpty()) {
            throw new ZaneException("The description of an event cannot be empty.");
        }
        Event event = new Event(description.trim(), from, to);
        tasks.addTask(event);
        storage.save(tasks);
        ui.printAddedTask(event, tasks.size());
    }
}
