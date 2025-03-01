import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> taskArrayList;

    /**
     * Initiates task list
     */
    public TaskList(){
        taskArrayList = new ArrayList<Task>();
    }

    /**
     *
     * @param load loads object into array list
     * @throws IOException IOEXCEPTION
     * @throws ClassNotFoundException CLASSNOTFOUND
     */
    public TaskList(ObjectInputStream load) throws IOException, ClassNotFoundException {
        taskArrayList = new ArrayList<Task>();
        taskArrayList = (ArrayList<Task>)load.readObject();
    }
}
