/**
 * Represents a Deadline with specified deadline date/time.
 */

public class Deadline extends Task {
    protected String by;

    /**
     *
     * @param description - Task description
     * @param by - Deadline (Date/Time)
     */

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     *
     * @return a string that contains specified task as well as deadline and current status
     */

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "]" + description + " (by: " + by + ")";
    }
}
