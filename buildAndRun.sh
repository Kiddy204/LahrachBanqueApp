#!/bin/sh
mvn clean package && docker build -t ma.lahrach/LahracheBanqueApp .
docker rm -f LahracheBanqueApp || true && docker run -d -p 9080:9080 -p 9443:9443 --name LahracheBanqueApp ma.lahrach/LahracheBanqueApp