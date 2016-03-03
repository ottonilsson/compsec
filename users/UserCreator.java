package users;

import java.io.*;
import data.*;

public class UserCreator implements Runnable {
    public final String classes = "[pnda]";  // patient, nurse, doctor or agency
    public final String fmt = "name:classID" + classes + ":division";

    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String userString = input.readLine();
                if (userString.matches("\\w+( \\w+)*:" + classes + "(:\\d+)?")) {
                    String[] fields = userString.split(":");
                    Person p;
                    String name = fields[0];
                    char classID = fields[1].charAt(0);
                    switch (classID) {
                        case 'p':
                            p = new Person(name);
                            break;
                        case 'n':
                            p = new Nurse(name, Integer.parseInt(fields[2]));
                            break;
                        case 'd':
                            p = new Doctor(name, Integer.parseInt(fields[2]));
                            break;
                        case 'a':
                            p = new Agency(name);
                            break;
                        default:
                            throw new UnsupportedOperationException(
                                    classID + " is not a class identifier");
                    }
                    if (Database.getInstance().addUser(p))
                        System.out.println("User " + name + " successfully added.");
                    else
                        System.out.println("User " + name + " already exists.");
                } else if (userString.equals("users")) {
                    System.out.println("Current existing users:");
                    for (Person p : Database.getInstance().users())
                        System.out.println(p);
                } else {
                    System.out.println("Format: " + fmt);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            (new Thread(this)).start();   // restart
        }
    }
}

