import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private String description;
    private String by;
    
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException {
        if (description == null || description.trim().isEmpty()) {
            throw new ZaneException("The description of a deadline cannot be empty.");
        }
        LocalDateTime date = Deadline.parseDate(by);
        Deadline deadline = new Deadline(description.trim(), date);
        tasks.addTask(deadline);
        storage.save(tasks);
        ui.printAddedTask(deadline, tasks.size());
    }
}
