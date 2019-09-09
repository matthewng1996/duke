import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Class that throws and test test cases.
 */

public class DukeTest {
    @Test
    public void TodoTest(){
        String string = "todo return books";
        String secondWord = string.substring(string.indexOf(" ") + 1);
        Task task = new Todo(string, secondWord);
        assertEquals("[T]" + "[" + "\u2718" + "]" + "return books", task.toString());
    }

    @Test
    public void DeadlineTest(){
        String string = "deadline buy book /by 9/9/2019 1700";
        String keyStroke = "/";
        int index = string.indexOf(keyStroke);
        String task = string.substring(string.indexOf(" "), index);
        String date = string.substring(index + 3);
        String temp = date;
        Task deadline = new Deadline(task, temp);
        assertEquals("[D]" + "[" + "\u2718" + "]" + deadline.description +  " (by: " + date + ")", deadline.toString());
    }

    @Test
    public void EventTest(){
        String string = "event buy book /at 9/9/2019 1700";
        String keyStroke = "/";
        int index = string.indexOf(keyStroke);
        String task = string.substring(string.indexOf(" "), index);
        String date = string.substring(index + 3);
        String temp = date;
        Task event = new Event(task, temp);
        assertEquals("[E]" + "[" + "\u2718" + "]" + event.description +  " (at: " + date + ")", event.toString());
    }

}