FROM amazoncorretto:17-alpine-jdk
LABEL authors="diegodevs"
COPY target/dmf-0.0.1-SNAPSHOT.jar dmf-0.0.1-SNAPSHOT
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/dmf-app.jar"]