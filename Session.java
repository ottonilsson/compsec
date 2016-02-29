import users.*;
import data.*;

import java.util.*;

public class Session {
    Database db = Database.getInstance();
    private ArrayList<Record> accessibleRecords;
    private Record selected;
    private Person subject, selectedPatient;
    private boolean creatingRecord;

    public Session(Person subject) {
        this.subject = subject;
        selected = null;
        selectedPatient = null;
        creatingRecord = false;
        if (subject instanceof Agency) {
            accessibleRecords = new ArrayList<Record>(db.records());
        } else {
            accessibleRecords = new ArrayList<Record>(subject.getRecords());
            if (subject instanceof Nurse) {
                Nurse n = (Nurse) subject;
                for (Record r : db.records()) {
                    if (r.division == n.division && !accessibleRecords.contains(r)) {
                        accessibleRecords.add(r);
                    }
                }
            }
        }
    }

    public String prompt() {
        String s = "";
        if (selected == null && !creatingRecord) {
            s += "Available records: \n";
            for (int i = 0; i < accessibleRecords.size(); i++) {
                s += i + ": " + accessibleRecords.get(i).toString() + "\n";
            }
            s += "a: Audit log\n";
            if (subject instanceof Doctor) {
                s += Action.Create.cmd + "\n";
            }
            s += "q: Quit\n";
        } else if (creatingRecord && selectedPatient == null) {
            s += printUsers(false);
            s += "Choose patient: ";
        } else if (creatingRecord && selectedPatient != null) {
            s += printUsers(true);
            s += "Choose nurse: ";
        } else {
            s += selected.printAccess(subject);
            s += "x: Close record";
        }
        return s;
    } 

    public String cmd(String input) {
        char c = input.charAt(0);
        if (selected == null) {
            switch (c) {
                case 'a':
                    return Log.getInstance().toString();
                case 'c':
                    if (subject instanceof Doctor) {
                        creatingRecord = true;
                        return "Creating new record:\n";
                    }
                    break;
                default:
                    try {
                        int index = Integer.parseInt(input);
                        if (creatingRecord) {
                            String createdMsg = "";
                            Person p;
                            if (selectedPatient != null) {
                                Nurse nurse = (Nurse) db.nurses().get(index);
				Record r = db.addRecord(selectedPatient, (Doctor) subject, nurse);
                                accessibleRecords.add(r);
                                creatingRecord = false;
                                createdMsg = "Record created for " + selectedPatient + ".\n";
                                selectedPatient = null;
				Log.getInstance().log(subject, Action.Create, r);
                                p = nurse;
                            } else {
                                selectedPatient = db.users().get(index);
                                p = selectedPatient;
                            }
                            return p + " selected.\n" + createdMsg; 
                        }
                        selected = accessibleRecords.get(index);
                        return "Record " + index + " selected.\n";
                    } catch (Exception e) {
                        e.printStackTrace();
                        return e.getMessage();
                    }
            }
        } else {
            switch(c) {
                case 'r':
                    if (selected.read(subject)) {
                        return "Record read.\n";
                    }
                    break;
                case 'w':
                    if (selected.write(subject)) {
                        return "Record written.\n";
                    }
                    break;
                case 'd':
                    if (selected.delete(subject)) {
                        accessibleRecords.remove(selected);
                        selected = null;
                        return "Record deleted.\n";
                    }
                    break;
                case 'x':
                    selected = null;
                    return "";
                default:
                    return "Invalid option\n";
            }
        }
        return "Access denied.\n";
    }

    private String printUsers(boolean onlyNurses) {
        String s = "";
        ArrayList<Person> u = new ArrayList<Person>(onlyNurses ? db.nurses() : db.users());
        for (int i = 0; i < u.size(); i++) {
            s += i + ": " +  u.get(i).toString() + "\n";
        }
        return s;
    }
}
