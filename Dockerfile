FROM ubuntu:latest
LABEL authors="diegodevs"
MAINTAINER diegodevs
COPY target/dmf-0.0.1-SNAPSHOT.jar dmf-app.jar
ENTRYPOINT ["java", "-jar", "/dmf-app.jar"]