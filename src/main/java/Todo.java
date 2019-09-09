/**
 * todo class.
 */
public class Todo extends Task{
    protected String task;

    /**
     *
     * @param description todo description
     * @param task specified task
     */
    public Todo(String description, String task) {
        super(description);
        this.task = task;
    }

    /**
     *
     * @return string that specifies current task status and description
     */
    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "]" + task;
    }
}
