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

        ArrayList<Task> taskList = new ArrayList<Task>();

        while (!myString.equals("bye"))
        {
            if (myString.equals("list") && taskList.size() > 0)
            {
                System.out.println("______________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++)
                {
                    Task task = taskList.get(i);
                    System.out.println((i+1) + "." + taskList.get(i));
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
            else if (myString.contains("todo")){
                String secondWord = myString.substring(myString.indexOf(" "));

                Task newTask = new Todo(myString, secondWord);
                taskList.add(newTask);

                System.out.println("______________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask.toString());
                if (taskList.size() == 1)
                    System.out.println("Now you have " + taskList.size() + " task in the list.");
                else
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                System.out.println("______________________________");
            }
            else if (myString.contains("deadline")){
                String keyStroke = "/";
                int index = myString.indexOf(keyStroke);
                String task = myString.substring(myString.indexOf(" "), index);
                String date = myString.substring(index + 3, myString.length());
                Task newTask = new Deadline(task, date);
                System.out.println("______________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask.toString());
                taskList.add(newTask);
                if (taskList.size() == 1)
                    System.out.println("Now you have " + taskList.size() + " task in the list.");
                else
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                System.out.println("______________________________");
            }
            else if (myString.contains("event")) {
                String keyStroke = "/";
                int index = myString.indexOf(keyStroke);
                String task = myString.substring(myString.indexOf(" "), index);
                String date = myString.substring(index + 3, myString.length());
                Task newTask = new Event(task, date);
                System.out.println("______________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask.toString());
                taskList.add(newTask);
                if (taskList.size() == 1)
                    System.out.println("Now you have " + taskList.size() + " task in the list.");
                else
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                System.out.println("______________________________");
            }
            else
            {
                System.out.println("______________________________");
                System.out.println("No such command");
                System.out.println("______________________________");
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