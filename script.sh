#!/bin/bash
# Script Project 1

# Client-side
echo 2.1.1
openssl genrsa -out ca.key 1024
openssl req -new -x509 -key ca.key -out ca.crt -subj "/C=SE/L=Lund/O=LTH/CN=CA"
echo 2.1.2
keytool -import -file ca.crt -keystore clienttruststore -storepass password -noprompt
echo 2.1.3
keytool -genkeypair -dname "CN=Hannah Lindblad (elt13hli)/Dwight Lidman (dat13dli)/Johan Ju (elt12jju)/Otto Nilsson (ael09oni)" -alias studentkey -keypass password -keystore clientkeystore -storepass password
echo 2.1.4
keytool -certreq -file lth.csr -alias studentkey -keystore clientkeystore -storepass password
echo 2.1.5
openssl x509 -req -in lth.csr -CA ca.crt -CAkey ca.key -CAcreateserial -out lth.crt
echo 2.1.6a
keytool -importcert -alias cakey -file ca.crt -keystore clientkeystore -storepass password -noprompt
echo 2.1.6b
keytool -importcert -alias studentkey -trustcacerts -file lth.crt -keystore clientkeystore -storepass password -noprompt
echo 2.1.7
keytool -list -v -keystore clientkeystore -storepass password

#Server-side
echo 2.1.9a
keytool -genkeypair -dname "CN=MyServer" -alias studentkey2 -keypass password -keystore serverkeystore -storepass password
echo 2.1.9b
keytool -certreq -file lth.csr -alias studentkey2 -keystore serverkeystore -storepass password
echo 2.1.9c
openssl x509 -req -in lth.csr -CA ca.crt -CAkey ca.key -CAcreateserial -out lth.crt
echo 2.1.9d
keytool -importcert -alias cakey2 -file ca.crt -keystore serverkeystore -storepass password -noprompt
keytool -importcert -alias studentkey2 -trustcacerts -file lth.crt -keystore serverkeystore -storepass password -noprompt
echo 2.1.10
keytool -importkeystore -srckeystore clienttruststore -destkeystore servertruststore -srcstorepass password -deststorepass password
#keytool -import -file ca.crt -keystore servertruststore -storepass password -noprompt


echo Finished!