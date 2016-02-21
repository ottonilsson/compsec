#!/bin/bash

# Client-side certificate
echo Create a trusttstore containing the CA certificate
keytool -import -file ca.crt -keystore clienttruststore -storepass govagencypwd -noprompt

echo Create a end-user keypair and a client-side keystore 
keytool -genkeypair -dname "CN=govagency" -alias govagency -keypass govagencypwd -keystore clientkeystore -storepass govagencypwd

echo Create a CSR for the keypair
keytool -certreq -file govagency.csr -alias govagency -keystore clientkeystore -storepass govagencypwd

echo Sign the CSR with the CA
openssl x509 -req -in govagency.csr -CA ca.crt -CAkey ca.key -CAcreateserial -out govagency.crt

echo Import the certificate chain into the keystore
keytool -importcert -alias cakey -file ca.crt -keystore clientkeystore -storepass govagencypwd -noprompt

keytool -importcert -alias govagency -trustcacerts -file govagency.crt -keystore clientkeystore -storepass govagencypwd -noprompt

echo Verifying the certificate chain
keytool -list -v -keystore clientkeystore -storepass govagencypwd

echo Done!