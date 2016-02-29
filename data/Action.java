package data;

public enum Action {
    Read    ("read from", "r: Read"),
    Write   ("wrote to", "w: Write"),
    Create  ("created", "c: Create"),
    Delete  ("deleted", "d: Delete");

    public final String verb,
                        cmd;

    Action(String verb, String cmd) {
        this.verb = verb;
        this.cmd = cmd;
    }
}
