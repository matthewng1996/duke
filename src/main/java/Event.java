/**
 * Class that specifies Event which is based off Task class
 */

public class Event extends Task {
    protected String at;

    /**
     *
     * @param description Event description
     * @param at Event time/date
     */

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     *
     * @return current status of the event with a specified date/time.
     */

    @Override
    public String toString()
    {
        return "[E]" + "[" + super.getStatusIcon() + "]" + description + " (at: " + at + ")";
    }
}
