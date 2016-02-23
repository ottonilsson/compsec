package data;

public class Record {
    public final Person patient;
    public final Doctor doctor;
    public final Nurse nurse;
    public final int division;

    public Record(int division, Person patient, Doctor doctor, Nurse nurse) {
        this.patient = patient;
        this.doctor = doctor;
        this.nurse = nurse;
        this.division = division;
    }

    public boolean read(Person subject) {
        return readwrite(subject, Action.Read);
    }
    
    public boolean write(Person subject) {
        return readwrite(subject, Action.Write);
    }
    
    public boolean delete(Person subject) {
        if (access(subject, Action.Delete)) {
            log(subject, Action.Delete);
            patient.removeNullRefs();
            nurse.removeNullRefs();
            doctor.removeNullRefs();
            this = null;
            return true;
        }
        return false;
    }

    private boolean readwrite(Person s, Action a) {
        if (access(s, a) {
            log(s, a);
            return true;
        }
        return false;
    }

    private boolean access(Person subject, Action action) {
        switch (a) {
            case Action.Read:
                if (subject.equals(patient)) {
                    return true;
                } else if (subject instanceof Nurse) {
                    if (subject.division == division)
                        return true;
                } else if (subject.equals(nurse) || subject.equals(doctor)) {
                    return true;
                } else if (subject instanceof Agency) {
                    return true;
                }
                break;
            case Action.Write:
                if (subject.equals(nurse) || subject.equals(doctor))
                    return true;
                break;
            case Action.Delete:
                if (subject instanceof Agency)
                    return true;
                break;
            default:
                break; // should throw exception
        }
        return false;
    }

    private void log(Person subject, Action action) {
        Log.getInstance().log(subject, action, this);
    }
}
