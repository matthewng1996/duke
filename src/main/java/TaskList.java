import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> taskArrayList;

    public TaskList(ObjectInputStream load) throws IOException, ClassNotFoundException {
        taskArrayList = new ArrayList<Task>();
        taskArrayList = (ArrayList<Task>)load.readObject();
    }
}
