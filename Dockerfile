FROM openjdk:8-jdk-alpine
COPY ["target/conferences-0.0.1-SNAPSHOT.jar", "conferences-0.0.1-SNAPSHOT.jar"]
EXPOSE 8081
ENTRYPOINT ["java","-jar","conferences-0.0.1-SNAPSHOT.jar"]