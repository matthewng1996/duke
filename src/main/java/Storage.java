import java.io.*;
import java.util.ArrayList;

public class Storage {

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

    public ObjectInputStream LoadData(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream input = new FileInputStream(filePath);
        ObjectInputStream load = new ObjectInputStream(input);
        return load;
    }
}
