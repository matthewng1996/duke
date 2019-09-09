/**
 * This class handles all errors by throwing a string message.
 */

public class DukeException extends Exception{

    public DukeException(String message) {
        super(message);
    }
}
