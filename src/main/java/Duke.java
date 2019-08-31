import com.sun.org.apache.bcel.internal.generic.FSTORE;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList taskList;

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    public Duke(String filePath)
    {
        storage = new Storage();
        try {
            taskList = new TaskList(storage.LoadData(filePath));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void run()
    {
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

        while (!myString.equals("bye"))
        {
            if (myString.equals("list"))
            {
                if (taskList.taskArrayList.size() > 0) {
                    try {
                        doList(taskList.taskArrayList);
                    } catch (DukeException e) {
                        System.out.println("______________________________");
                        System.out.println(e);
                        System.out.println("______________________________");
                    }
                }
                else {
                    System.out.println("______________________________");
                    System.out.println("List is empty!");
                    System.out.println("______________________________");
                }

            }
            else if (myString.indexOf("done") == 0) {
                try {
                    doneTask(myString, taskList.taskArrayList);
                } catch (DukeException e) {
                    System.out.println("______________________________");
                    System.out.println(e);
                    System.out.println("______________________________");
                }
            }
            else if (myString.indexOf("todo") == 0){
                try {
                    todo(myString, taskList.taskArrayList);
                } catch (DukeException e) {
                    System.out.println("______________________________");
                    System.out.println(e);
                    System.out.println("______________________________");
                }
            }
            else if (myString.indexOf("deadline") == 0){
                try {
                    deadline(myString, taskList.taskArrayList);
                } catch (DukeException e) {
                    System.out.println("______________________________");
                    System.out.println(e);
                    System.out.println("______________________________");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else if (myString.indexOf("event") == 0) {
                try {
                    event(myString, taskList.taskArrayList);
                } catch (DukeException | ParseException e) {
                    System.out.println("______________________________");
                    System.out.println(e);
                    System.out.println("______________________________");
                }
            }

            else if (myString.indexOf("delete") == 0) {
                try {
                    deleteTask(myString, taskList.taskArrayList);
                } catch (DukeException e) {
                    e.printStackTrace();
                }
            }
            else if (myString.indexOf("find") == 0)
            {
                String secondWord = myString.substring(myString.indexOf(" "));

                try {
                    findKeywords(secondWord, taskList.taskArrayList);

                } catch (DukeException e) {
                    System.out.println(e);
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
            storage.SaveData(taskList.taskArrayList);
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

    public static void deleteTask(String myString, ArrayList<Task> taskList) throws DukeException{
        if (myString.indexOf(" ") == -1)
        {
            throw new DukeException("☹ OOPS!!! Delete command needs a relevant number");
        }
        else
        {
            String secondWord = myString.substring(myString.indexOf(" "));
            secondWord = secondWord.replaceAll("\\s", "");
            int taskIndex = Integer.parseInt(secondWord);

            Task task = taskList.get(taskIndex - 1);

            taskList.remove(taskIndex - 1);
            System.out.println("______________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println(task.toString());
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

    public static void deadline (String myString, ArrayList<Task> taskList) throws DukeException, ParseException {
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
            String temp = date;

            date = date.replaceAll(" ", "");

            if (date.isEmpty())
                throw new DukeException("☹ OOPS!!! I'm sorry, but you need a date/time for your deadline");
            else if (temp.matches(" ([0-9]{1,2})/([0-9]{1,2})/([0-9]{4}) ([0-9]{4})")) {
                SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyy HHmm");
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyy, h:mma"); //Desired format
                Date dateInput = parser.parse(temp);
                date = formatter.format(dateInput);
                System.out.println("Your specified deadline is " + date);
            } else if (temp.matches(" ([0-9]{1,2})/([0-9]{1,2})/([0-9]{4})")) {
                SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyy");
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyy"); //Desired Format
                Date dateInput = parser.parse(temp);
                date = formatter.format(dateInput);
                System.out.println("Your specified deadline is " + date);
            } else
                System.out.println("Do note that you did not specify any deadline though.");

            Task newTask = new Deadline(task, temp);
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

    public static void event (String myString, ArrayList<Task> taskList) throws DukeException, ParseException {
        String keyStroke = "/";
        int index = myString.indexOf(keyStroke);
        String description = myString.substring(myString.indexOf(" ") + 1, index);
        description = description.replaceAll(" ", "");

        if (description.isEmpty())
            throw new DukeException("☹ OOPS!!! The description of event cannot be empty.");

        if (index == -1)
            throw new DukeException("Event comes with a /at!");

        else {
            String task = myString.substring(myString.indexOf(" "), index);
            String date = myString.substring(index + 3);
            String temp = date;

            date = date.replaceAll(" ", "");

            if (date.isEmpty())
                throw new DukeException("☹ OOPS!!! I'm sorry, but you need a date/time for your event");
            else if (temp.matches(" ([0-9]{1,2})/([0-9]{1,2})/([0-9]{4}) ([0-9]{4})")) {
                SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyy HHmm");
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyy, h:mma"); //Desired format
                Date dateInput = parser.parse(temp);
                date = formatter.format(dateInput);
                System.out.println("Your specified deadline is " + date);
            } else if (temp.matches(" ([0-9]{1,2})/([0-9]{1,2})/([0-9]{4})")) {
                SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyy");
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyy"); //Desired Format
                Date dateInput = parser.parse(temp);
                date = formatter.format(dateInput);
                System.out.println("Your specified event is on the " + date);
            } else
                System.out.println("Do note that you did not specify any date/time for event though.");

            Task newTask = new Event(task, temp);
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

    public static void findKeywords(String keyword, ArrayList<Task> taskList) throws DukeException
    {
        String temp = keyword;
        temp = temp.replaceAll(" ", "");

        if (temp.isEmpty())
            throw new DukeException("Empty search");

        ArrayList<Task> keywordList = new ArrayList<Task>();

        for (int i = 0; i < taskList.size(); i++)
        {
            if (taskList.get(i).description.contains(keyword))
                keywordList.add(taskList.get(i));
        }

        System.out.println("______________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < keywordList.size(); i++)
            System.out.println(i+1+ ". " + keywordList.get(i).toString());
        System.out.println("______________________________");
    }
}