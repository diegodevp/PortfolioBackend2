FROM amazoncorretto:20-alpine-jdk
LABEL authors="diegodevs"
COPY target/dmf-0.0.1-SNAPSHOT.jar dmf-app.jar
PORT/8080/
ENTRYPOINT ["java", "-jar", "/dmf-app.jar"]