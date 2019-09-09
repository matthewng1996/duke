import java.io.*;
import java.util.ArrayList;

/**
 * Storage class
 */
public class Storage {

    /**
     *
     * @param taskList current list of task
     */
    public void SaveData(ArrayList<Task> taskList)
    {
        try {
            //Open file
            FileOutputStream saveFile = new FileOutputStream("duke.txt");

            //Objects into save file
            ObjectOutputStream save = new ObjectOutputStream(saveFile);

            //Saving the taskList
            //for (Task task : taskList) {
                save.writeObject(taskList);
           // }

            save.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filePath path of save/load file
     * @return return object
     * @throws IOException IOException
     * @throws ClassNotFoundException file not found
     */
    public ObjectInputStream LoadData(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream input = new FileInputStream(filePath);
        ObjectInputStream load = new ObjectInputStream(input);
        return load;
    }
}
