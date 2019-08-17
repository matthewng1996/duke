import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner input = new Scanner(System.in);
        String myString = new String();
        System.out.println("______________________________");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("______________________________");
        myString = input.nextLine();

        ArrayList<String> list = new ArrayList<String>();
        ArrayList<Task> taskList = new ArrayList<Task>();

        while (!myString.equals("bye"))
        {
            if (myString.equals("list") && list.size() > 0)
            {
                System.out.println("______________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++)
                {
                    Task task = taskList.get(i);
                    System.out.println((i+1) + "." + "[" + task.getStatusIcon() +"] " + list.get(i));
                }
                System.out.println("______________________________");
            }
            else if (myString.contains("done")) {
                String secondWord = myString.substring(myString.indexOf(" "));
                secondWord = secondWord.replaceAll("\\s","");
                int taskIndex = Integer.parseInt(secondWord);

                Task task = taskList.get(taskIndex - 1);
                task.isDone = true;
                System.out.println("______________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + task.getStatusIcon() + "] " + task.description);
                System.out.println("______________________________");
            }
            else {
                    System.out.println("______________________________");
                    System.out.println("added: " + myString);
                    System.out.println("______________________________");
                    list.add(myString);
                    Task newTask = new Task(myString);
                    taskList.add(newTask);
            }
            myString = input.nextLine();
        }

        if (myString.equals("bye"))
        {
            System.out.println("______________________________");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("______________________________");
            System.exit(0);
        }
    }
}