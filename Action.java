public enum Action {
    Read    ("read from", "r: Read"),
    Write   ("wrote to", "w: Write"),
    Create  ("created", "c: Create"),
    Delete  ("deleted", "d: Delete"),
    Audit   ("audited", "a: Audit (print log)");

    final String verb,
                 msg;

    Action(String verb, String msg) {
        this.verb = verb;
        this.msg = msg;
    }
}
