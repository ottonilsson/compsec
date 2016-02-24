package users;

import java.util.*;
import data.*;

public class Person {
    public final String name;
    private ArrayList<Record> records;

    public Person(String name) {
        records = new ArrayList<Record>();
        this.name = name;
    }

    public void addRecord(Record r) {
        records.add(r);
    }

    public List<Record> getRecords() {
        return new LinkedList<Record>(records);
    }

    public void removeRecord(Record r) {
        records.remove(r);
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o == this)
                return true;
            if (o instanceof Person) {
                return ((Person) o).name.equals(this.name);
            }
        }
        return false;
    }
}
