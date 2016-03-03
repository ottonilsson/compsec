<!-- This document is formatted with markdown syntax, the HTML version is preferred for end users. -->
__Note:__ All values denoted with ```<brackets>``` should be typed without them.
# Installation #
## Linux/Mac ##
Run the 'compile.sh' script.
## Windows ##
Translate the 'compile.sh' to a batch script and run that.
# Execution #
Start the server:
```
    java server <port>
```

Connect to the server:
```
    java client <target host> <port>
```
# Usage #
## Server ##
The server will print information regarding connected clients in its terminal window.
New users are created by entering a string formatted in this way into the terminal:

```
    <name>:<classID>:<division>
```

- _name_ - any alphanumeric string (```a-Z0-9_``` only, no international characters)
- _classID_ - 'p' for patient, 'n' for nurse, 'd' for doctor or 'a' for (government) agency
- _division_ - any integer

__Example:__

```
    Mantis Toboggan:d:100
```

Will create a user named "Mantis Toboggan", who is a doctor in division 100.

Currently existing users may be listed by typing:

```
    users
```

## Client ##
The keystores to be used when logging in to the server is the _clientkeystore_ and _clienttruststore_ placed in the same directory as the client.class binary executable.

After entering the correct password and connecting to the server, the user is presented with all available actions. Simply entering the letter or number associated with the action (the part to the left of the colon, that is) and pressing enter will tell the server that the user wishes to perform this action.
When first starting the program, there may not be any records visible. After creating a record, it will show up for any user that has some form of access to the record. Pressing 'u' to update the screen may be necessary if the user has not performed an action after the record being created.
## Test values ##
For added convenience when testing the program, 10 example users with associated certificates are included (hard coded) in the program. If one wishes to remove these, one would have to remove or comment out the _hardCodedUsers()_ method in the _Database.java_ source code file and recompile.

The keystores for each test user is in an appropriately named directory inside the certs directory. Running the _compile.sh_ script will make a copy of the client.class binary in each of these folders, to easier facilitate the testing of multiple concurrently connected users.
