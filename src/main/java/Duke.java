import java.util.Scanner;
import java.util.stream.StreamSupport;

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
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        System.out.println("______________________________");
        myString = input.nextLine();

        while (!myString.equals("bye"))
        {
            System.out.println("______________________________");
            System.out.println(myString);
            System.out.println("______________________________");
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