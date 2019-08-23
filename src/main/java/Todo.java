public class Todo extends Task{
    protected String task;

    public Todo(String description, String task) {
        super(description);
        this.task = task;
    }

    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "]" + task;
    }
}
