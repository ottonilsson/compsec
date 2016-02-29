package data;

import java.time.Clock;
import java.util.*;
import users.*; 

// Singleton implementation of the audit log
public class Log {
    private static Log instance = null;
    private LinkedList<String> events;

    protected Log() {
        events = new LinkedList<String>();
    }

    public static Log getInstance() {
        if (instance == null)
            instance = new Log();
        return instance;
    }

    public void log(Person subject, Action action, Record record) {
        String eventString = System.currentTimeMillis() + ": " +
            subject.name + " " + action.verb +
            " record in division " + record.division +
            ", concerning " + record.patient.name;
        events.add(eventString);
    }

    public String toString() {
        String log = "";
        for (String s : events) {
            log += s + "\n";
        }
        return log;
    }
}
