#!/bin/bash

# Server-side certificate
echo Create a server keypair and a server-side keystore
keytool -genkeypair -dname "CN=Server" -alias server -keypass serverpwd -keystore serverkeystore -storepass serverpwd

echo Create a CSR for the keypair
keytool -certreq -file server.csr -alias server -keystore serverkeystore -storepass serverpwd

echo Sign the CSR with the CA
openssl x509 -req -in server.csr -CA ca.crt -CAkey ca.key -CAcreateserial -out server.crt

echo Import the certificate chain into the keystore
keytool -importcert -alias cakey2 -file ca.crt -keystore serverkeystore -storepass serverpwd -noprompt
keytool -importcert -alias server -trustcacerts -file server.crt -keystore serverkeystore -storepass serverpwd -noprompt

echo Create a server-side truststore
keytool -import -file ca.crt -keystore servertruststore -storepass serverpwd -noprompt
#keytool -importkeystore -srckeystore clienttruststore -destkeystore servertruststore -srcstorepass serverpwd -deststorepass serverpwd

echo Done!