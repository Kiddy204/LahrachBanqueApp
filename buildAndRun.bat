@echo off
call mvn clean package
call docker build -t ma.lahrach/LahracheBanqueApp .
call docker rm -f LahracheBanqueApp
call docker run -d -p 9080:9080 -p 9443:9443 --name LahracheBanqueApp ma.lahrach/LahracheBanqueApp