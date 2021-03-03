# KartGRPC

This project is for my learning on GRPC Client and Server based project with best practices

Trying to build a cart management system

What it has right now

In Memory Data for the following

1. Products
2. Categories
3. Users - TODO



Authentication

1. Setup SSL/TLS mechanism

![Alt text](generatecert.png?raw=true "Generate local certs")

        How to generate local certs ? Keep it simple!

        // I recommend using mkcert to generate local certificates.
        
        JUST RUN

        $ mkcert -key-file key.pem -cert-file cert.pem localhost

        The certificate is at "cert.pem" and the key at "key.pem" ✅

        $ mkcert -CAROOT



2. JWT auth - In Progress

3. ....
   
