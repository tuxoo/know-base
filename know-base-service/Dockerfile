FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY target/know-base-service-0.0.1-SNAPSHOT.jar /app
EXPOSE ${SERVICE_PORT}

ENTRYPOINT ["java", "-jar", "/app/know-base-service-0.0.1-SNAPSHOT.jar"]