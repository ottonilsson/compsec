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
}
