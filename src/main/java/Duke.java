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
                try {
                    doList(taskList);
                } catch (DukeException e) {
                    System.out.println("______________________________");
                    System.out.println(e);
                    System.out.println("______________________________");
                }
            }
            else if (myString.contains("done")) {
               try {
                   doneTask(myString, taskList);
               } catch (DukeException e) {
                   System.out.println("______________________________");
                   System.out.println(e);
                   System.out.println("______________________________");
               }
            }
            else if (myString.contains("todo")){
                try {
                    todo(myString, taskList);
                } catch (DukeException e) {
                    System.out.println("______________________________");
                    System.out.println(e);
                    System.out.println("______________________________");
                }
            }
            else if (myString.contains("deadline")){
                try {
                    deadline(myString, taskList);
                } catch (DukeException e) {
                    System.out.println("______________________________");
                    System.out.println(e);
                    System.out.println("______________________________");
                }
            }
            else if (myString.contains("event")) {
                try {
                    event(myString, taskList);
                } catch (DukeException e) {
                    System.out.println("______________________________");
                    System.out.println(e);
                    System.out.println("______________________________");
                }
            }
            else
            {
                System.out.println("______________________________");
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
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

    public static void doList(ArrayList<Task> taskList) throws DukeException {
        System.out.println("______________________________");
        System.out.println("Here are the tasks in your list:");

        if (taskList.size() == 0)
            throw new DukeException("Your list is empty!");
        else {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println((i + 1) + "." + taskList.get(i));
            }
        }
        System.out.println("______________________________");
    }

    public static void doneTask(String myString, ArrayList<Task> taskList) throws DukeException{
        if (myString.indexOf(" ") == -1)
        {
            throw new DukeException("☹ OOPS!!! Done command needs a relevant number");
        }
        else {
            String secondWord = myString.substring(myString.indexOf(" "));
            secondWord = secondWord.replaceAll("\\s", "");
            int taskIndex = Integer.parseInt(secondWord);

            Task task = taskList.get(taskIndex - 1);
            task.isDone = true;

            System.out.println("______________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + task.getStatusIcon() + "] " + task.description);
            System.out.println("______________________________");
        }
    }

    public static void todo(String myString, ArrayList<Task> taskList) throws DukeException{

        if (myString.indexOf(" ") == -1)
        {
            throw new DukeException("☹ OOPS!!! The description of todo cannot be empty.");
        }
        else {
            String secondWord = myString.substring(myString.indexOf(" ") + 1);

            if (secondWord.isEmpty())
                throw new DukeException("☹ OOPS!!! The description of todo cannot be empty.");

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
    }

    public static void deadline (String myString, ArrayList<Task> taskList) throws DukeException{
        String keyStroke = "/";
        String description = myString.substring(myString.indexOf(" ") + 1, myString.indexOf(keyStroke));
        description = description.replaceAll(" ", "");

        if (description.isEmpty())
            throw new DukeException("☹ OOPS!!! The description of deadline cannot be empty.");

        int index = myString.indexOf(keyStroke);
        if (index == -1)
            throw new DukeException("Deadline comes with a /by!");
        else {
            String task = myString.substring(myString.indexOf(" "), index);
            String date = myString.substring(index + 3);
            date = date.replaceAll(" ", "");

            if (date.isEmpty())
                throw new DukeException("☹ OOPS!!! I'm sorry, but you need a date/time for your deadline");

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
    }

    public static void event (String myString, ArrayList<Task> taskList) throws DukeException{
        String keyStroke = "/";
        int index = myString.indexOf(keyStroke);
        String description = myString.substring(myString.indexOf(" ") + 1, index);
        description = description.replaceAll(" ", "");

        if (description.isEmpty())
            throw new DukeException("☹ OOPS!!! The description of event cannot be empty.");

        if (index == -1)
            throw new DukeException("Deadline comes with a /at!");

        else {
            String task = myString.substring(myString.indexOf(" "), index);
            String date = myString.substring(index + 3);
            date = date.replaceAll(" ", "");

            if (date.isEmpty())
                throw new DukeException("☹ OOPS!!! I'm sorry, but you need a time for your event");

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
    }
}