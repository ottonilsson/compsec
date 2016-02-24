package users;

public class Nurse extends Person {
    public final int division;

    public Nurse(String name, int division) {
        super(name);
        this.division = division;
    }
}
