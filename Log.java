package data;

import java.time.Clock;

// Singleton implementation of the audit log
public class Log {
    private static Log instance = null;
    private Clock clock;
    private LinkedList<String> events;

    protected Log() {
        clock = new Clock();
        events = new LinkedList<String>();
    }

    public Log getInstance() {
        if (instance == null)
            instance = new Log();
        return instance;
    }

    public void log(Person subject, Action action, Record record) {
        String eventString = clock.millis() + ": " +
            subject.name + " " + action.verb +
            " record in division " + record.division +
            ", concerning " + patient.name;
        events.add(eventString);
    }
}
