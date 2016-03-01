package data;

import java.util.*;
import users.*;

public class Database {
    private static Database instance = null;
    private static ArrayList<Record> records = null;
    private static ArrayList<Person> users = null;

    protected Database() {
        records = new ArrayList<Record>();
        // users = new ArrayList<Person>();
        users = hardCodedUsers();
    }

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    public static List<Record> records() {
        return new ArrayList<Record>(records);
    }

    public static List<Person> users() {
        return new ArrayList<Person>(users);
    }

    public static List<Person> nurses() {
        ArrayList<Person> n = new ArrayList<Person>();
        for (Person p : users) {
            if (p instanceof Nurse && !(p instanceof Doctor)) {
                n.add(p);
            }
        }
        return n;
    }

    public static Record addRecord(Person p, Doctor d, Nurse n) {
        Record r = new Record(d.division, p, d, n);
        p.addRecord(r);
        d.addRecord(r);
        n.addRecord(r);
        records.add(r);
        return r;
    }

    public static void removeRecord(Record r) {
        records.remove(r);
    }

    // hard coded user accounts for testing 
    private ArrayList<Person> hardCodedUsers() {
        ArrayList<Person> u = new ArrayList<Person>();
        u.add(new Person("patient_1"));
        u.add(new Person("patient_2"));
        u.add(new Person("patient_3"));
        u.add(new Nurse("nurse_1", 1));
        u.add(new Nurse("nurse_2", 2));
        u.add(new Nurse("nurse_3", 3));
        u.add(new Doctor("doctor_1", 1));
        u.add(new Doctor("doctor_2", 2));
        u.add(new Doctor("doctor_3", 3));
        u.add(new Agency("govagency"));
        return u;
    }
}
