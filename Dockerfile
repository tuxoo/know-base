FROM openjdk:17-jdk-alpine
WORKDIR /app

ARG JAR_FILE=target/know-base-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/know-base-0.0.1-SNAPSHOT.jar"]