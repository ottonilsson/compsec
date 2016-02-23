public class Record {
    final Person patient;
    final Doctor doctor;
    final Nurse nurse;
    final int division;

    public Record(int division, Person patient, Doctor doctor, Nurse nurse) {
        this.patient = patient;
        this.doctor = doctor;
        this.nurse = nurse;
        this.division = division;
    }

    public Set<Action> access(Person subject) {
        Set<Action> permissions = EnumSet<Action>();
        if (subject.equals(patient))
            permissions.add(Action.Read);
        if (subject instanceof Nurse) {
            if (subject.division == division) {
                permissions.add(Action.Read);
            } else if (subject.equals(nurse) || subject.equals(doctor)) {
                permissions.add(Action.Read);
                permissions.add(Action.Write);
            }
        }
        if (subject instanceof Agency) {
            permissions.add(Action.Read);
            permissions.add(Action.Delete);
        }
        return permissions;
    }

    public void read() {}
    
    public void write() {}
    
    public void delete() {
        // log()
        this = null;
    }
}
