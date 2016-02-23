package users;

import java.util.ArrayList;

public class Person {
    final String name;
    private ArrayList<Record> records;

    public Person(String name) {
        records = new ArrayList<Record>();
        this.name = name;
    }

    public void addRecord(Record r) {
        records.add(r);
    }

    public List<Record> getRecords() {
        return new List<Record>(records);
    }

    public void removeNullRefs() {
        records.removeAll(Collections.singleton(null));
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
