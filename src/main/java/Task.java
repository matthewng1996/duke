import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class handles all task and is serialized for storage.
 */

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     *
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     *
     * @return task status for all task, either a tick for done, or a cross for an undone task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
}