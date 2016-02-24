package data;

import java.util.*;
import users.*;

public class Database {
    private static Database instance = null;
    private static LinkedList<Record> records = null;
    private static LinkedList<Person> users = null;

    protected Database() {
        records = new LinkedList<Record>();
        // users = new LinkedList<Person>();
        users = hardCodedUsers();
    }

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    public static List<Record> records() {
        return new LinkedList<Record>(records);
    }

    public static List<Person> users() {
        return new LinkedList<Person>(users);
    }

    // hard coded user accounts for testing 
    private LinkedList<Person> hardCodedUsers() {
        LinkedList<Person> u = new LinkedList<Person>();
        u.add(new Person("patient_1"));
        u.add(new Person("patient_2"));
        u.add(new Person("patient_3"));
        u.add(new Nurse("nurse_1", 1));
        u.add(new Nurse("nurse_2", 1));
        u.add(new Nurse("nurse_3", 2));
        u.add(new Doctor("doctor_1", 1));
        u.add(new Doctor("doctor_2", 2));
        u.add(new Doctor("doctor_3", 3));
        return u;
    }
}
