#!/bin/bash
javac *.java data/*.java users/*.java; echo certs/doctor_* certs/nurse_* certs/patient_* certs/govagency | xargs -n 1 cp client.class
