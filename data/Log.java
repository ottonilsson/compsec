package data;

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
        String eventString = "[" + time() +  "]\t" +
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

    private String time() {
        Calendar cal = Calendar.getInstance();
        StringBuilder time = new StringBuilder();
        time.append(cal.get(Calendar.YEAR) + "/");
        time.append(format(cal.get(Calendar.MONTH)) + "/");
        time.append(format(cal.get(Calendar.DAY_OF_MONTH)) + " ");
        time.append(format(cal.get(Calendar.HOUR_OF_DAY)) + ":");
        time.append(format(cal.get(Calendar.MINUTE)) + ":");
        time.append(format(cal.get(Calendar.SECOND)));
        return time.toString();
    }

    private String format(int n) {
        return n < 10 ? "0" + n : "" + n;
    }
}
