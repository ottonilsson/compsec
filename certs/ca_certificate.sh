#!/bin/bash

# CA certificate
echo Generate RSA key
openssl genrsa -out ca.key 1024

echo Create new x509 CA certificate
openssl req -new -x509 -key ca.key -out ca.crt -subj "/C=SE/L=Lund/O=Hospital/CN=CA"

echo Done!